package com.wilber.ident_core_api.auth.verification.service;

import com.wilber.ident_core_api.auth.verification.dto.EmailTokenDTO;
import com.wilber.ident_core_api.user.entity.UserEntity;

public interface IEmailTokenService {

    String createToken();
    String createLink(String tokenVerification);
    void savedToken(UserEntity userEntity);
    EmailTokenDTO findEmailTokenByUser(UserEntity userEntity);
    void markExpiredEmailTokes();

}
