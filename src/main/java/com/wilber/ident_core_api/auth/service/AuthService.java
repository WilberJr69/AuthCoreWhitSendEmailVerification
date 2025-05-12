package com.wilber.ident_core_api.auth.service;

import com.wilber.ident_core_api.auth.dto.LoginRequest;
import com.wilber.ident_core_api.auth.dto.RegisterRequest;
import com.wilber.ident_core_api.auth.verification.entity.EmailTokenEntity;
import com.wilber.ident_core_api.auth.verification.repo.EmailTokenRepo;
import com.wilber.ident_core_api.auth.verification.service.EmailTokenService;
import com.wilber.ident_core_api.email.service.IEmailService;
import com.wilber.ident_core_api.role.service.IRoleService;
import com.wilber.ident_core_api.security.jwt.dto.CustomUserDetails;
import com.wilber.ident_core_api.security.jwt.dto.JwtResponseDTO;
import com.wilber.ident_core_api.security.jwt.service.JwtService;
import com.wilber.ident_core_api.user.entity.UserEntity;
import com.wilber.ident_core_api.user.map.UserMap;
import com.wilber.ident_core_api.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService{

    private final UserMap userMap;
    private final UserRepo userRepo;
    private final IRoleService roleService;
    private final EmailTokenService linkService;
    private final IEmailService emailService;
    private final EmailTokenRepo emailTokenRepo;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void registerUser(RegisterRequest registerRequest){

        UserEntity savedUser = userMap.toUserEntity(registerRequest);
        savedUser.setRole(
                roleService.findRoleByRoleName("ROLE_PRE_USER")
        );
        savedUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepo.save(savedUser);

        linkService.savedToken(savedUser);
        String tokenVerification = linkService.findEmailTokenByUser(savedUser).getVerificationToken();
        String linkVerification = linkService.createLink(tokenVerification);

        emailService.sendEmail(
                new String[]{registerRequest.getEmail()},
                "Paso final para confirmación de su registro",
                "Comlete su registro con el siguiente link: "+linkVerification
        );

    }

    @Transactional
    @Override
    public void verificationEmail(String token){

        EmailTokenEntity emailTokenEntity = emailTokenRepo.findValidVerificationToken(token)
                .orElseThrow(()->new RuntimeException("Token inválido, expirado o ya usado"));

        UserEntity user = emailTokenEntity.getUserEntity();
        user.setRole(
                roleService.findRoleByRoleName("ROLE_USER")
        );
        user.setVerified(true);
        emailTokenEntity.setUsed(true);

        emailTokenRepo.save(emailTokenEntity);
        userRepo.save(user);

    }

    @Override
    public JwtResponseDTO login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String jwtToken = jwtService.generateToken(userDetails);

        return new JwtResponseDTO(
                jwtToken,
                userDetails.getUserEntity().getUsername(),
                userDetails.getUserEntity().getEmail(),
                userDetails.getUserEntity().getRole().getRoleName()
        );
    }


}
