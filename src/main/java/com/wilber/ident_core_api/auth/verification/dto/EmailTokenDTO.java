package com.wilber.ident_core_api.auth.verification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailTokenDTO {

    private String verificationToken;

}
