package com.wilber.ident_core_api.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDTO {

    private String token;
    private String userName;
    private String email;
    private String role;

}
