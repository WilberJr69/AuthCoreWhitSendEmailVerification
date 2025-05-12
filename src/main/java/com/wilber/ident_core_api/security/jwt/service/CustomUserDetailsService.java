package com.wilber.ident_core_api.security.jwt.service;

import com.wilber.ident_core_api.security.jwt.dto.CustomUserDetails;
import com.wilber.ident_core_api.user.entity.UserEntity;
import com.wilber.ident_core_api.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepo.findByEmail(email)
                .orElseThrow(()->new RuntimeException("Usuario no encontrado"));

        return new CustomUserDetails(user);
    }
}
