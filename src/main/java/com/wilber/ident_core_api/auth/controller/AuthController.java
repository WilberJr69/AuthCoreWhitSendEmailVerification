package com.wilber.ident_core_api.auth.controller;

import com.wilber.ident_core_api.auth.dto.LoginRequest;
import com.wilber.ident_core_api.auth.dto.RegisterRequest;
import com.wilber.ident_core_api.auth.service.IAuthService;
import com.wilber.ident_core_api.security.jwt.dto.JwtResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/register_user")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterRequest registerRequest){
        authService.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente");
    }

    @GetMapping(value = "/confirm_email", params = {"token"})
    public ResponseEntity<String> confirmEmail(@RequestParam String token){
        authService.verificationEmail(token);
        return ResponseEntity.ok("Correo verificado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody @Valid LoginRequest loginRequest){
        JwtResponseDTO jwtResponse = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtResponse);
    }









}
