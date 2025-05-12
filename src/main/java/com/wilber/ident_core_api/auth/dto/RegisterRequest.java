package com.wilber.ident_core_api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String userName;

    @Email(message = "El correo debe tener un formato valido")
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @Size(min = 8, message = "El password debe tener al menos 8 caracteres")
    @NotBlank(message = "El password es obligatorio")
    private String password;

}
