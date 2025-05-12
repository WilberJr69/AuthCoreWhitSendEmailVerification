package com.wilber.ident_core_api.auth.service;

import com.wilber.ident_core_api.auth.dto.LoginRequest;
import com.wilber.ident_core_api.auth.dto.RegisterRequest;
import com.wilber.ident_core_api.security.jwt.dto.JwtResponseDTO;

public interface IAuthService {

    void registerUser(RegisterRequest registerRequest);
    void verificationEmail(String token);
    JwtResponseDTO login(LoginRequest request);

}
