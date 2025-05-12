package com.wilber.ident_core_api.auth.verification.service;

import com.wilber.ident_core_api.auth.verification.dto.EmailTokenDTO;
import com.wilber.ident_core_api.auth.verification.entity.EmailTokenEntity;
import com.wilber.ident_core_api.auth.verification.map.EmailTokenMap;
import com.wilber.ident_core_api.auth.verification.repo.EmailTokenRepo;
import com.wilber.ident_core_api.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailTokenService implements IEmailTokenService {

    private final EmailTokenRepo emailTokenRepo;
    private final EmailTokenMap emailTokenMap;

    @Override
    public String createToken(){
        return UUID.randomUUID().toString();
    }

    @Override
    public String createLink(String tokenVerification) {
        return "http://localhost:8080/auth/confirm_email?token="+ tokenVerification;
    }

    @Override
    public void savedToken(UserEntity userEntity) {

        EmailTokenEntity emailTokenEntity= new EmailTokenEntity();

        emailTokenEntity.setVerificationToken(createToken());
        emailTokenEntity.setCreatedAt(LocalDateTime.now());
        emailTokenEntity.setExpiresAt(LocalDateTime.now().plusMinutes(3));
        emailTokenEntity.setUsed(false);
        emailTokenEntity.setExpired(false);
        emailTokenEntity.setUserEntity(userEntity);

        emailTokenRepo.save(emailTokenEntity);

    }

    @Override
    public EmailTokenDTO findEmailTokenByUser(UserEntity userEntity){

        EmailTokenEntity emailTokenEntity = emailTokenRepo.findByUserEntity(userEntity)
                .orElseThrow(()->new RuntimeException("Ese ususario no tiene un link de verificacion asignado"));

        return emailTokenMap.toLinkDTO(emailTokenEntity);

    }

    

    @Transactional
    @Scheduled(fixedRate = 300000) //5 minutes
    @Override
    public void markExpiredEmailTokes(){

        List<EmailTokenEntity> expiredEmailTokes = emailTokenRepo.findTokensExpired(LocalDateTime.now());
        expiredEmailTokes.forEach(c -> c.setExpired(true));
        emailTokenRepo.saveAll(expiredEmailTokes);

    }







}
