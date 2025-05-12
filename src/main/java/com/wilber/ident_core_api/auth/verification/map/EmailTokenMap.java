package com.wilber.ident_core_api.auth.verification.map;

import com.wilber.ident_core_api.auth.verification.dto.EmailTokenDTO;
import com.wilber.ident_core_api.auth.verification.entity.EmailTokenEntity;
import org.springframework.stereotype.Component;

@Component
public class EmailTokenMap {

    public EmailTokenDTO toLinkDTO(EmailTokenEntity emailTokenEntity){

        EmailTokenDTO emailTokenDTO = new EmailTokenDTO();
        emailTokenDTO.setVerificationToken(emailTokenEntity.getVerificationToken());

        return emailTokenDTO;

    }

}
