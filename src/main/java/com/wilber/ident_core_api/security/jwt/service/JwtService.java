package com.wilber.ident_core_api.security.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Clave secreta codificada en Base64 para firmar el token
    @Value("${app.secret.jwt}")
    private String SECRET_KEY;

    // Tiempo de expiración del token en milisegundos (ej. 30 minutos)
    private static final long EXPIRATION_TIME = 1000 * 60 * 30;

    public String generateToken(UserDetails userDetails){

        Map<String, Object> claims = new HashMap<>();

        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");

        claims.put("role",role);

        return createToken(claims, userDetails.getUsername());

    }

    private String createToken(Map<String,Object> claims, String userName){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(getSignkey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignkey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // ---------------------------------------------------
    // -------- Métodos para validación del token --------
    // ---------------------------------------------------

    //Extrae el nombre de usuario (subject) desde el token
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //Valida si el token pertenece al usuario y si no está expirado
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    //Verifica si el token ya está expirado
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

     //Extrae la fecha de expiración desde el token.

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

     //Extrae cualquier claim del token utilizando una función

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Parsea el token y extrae todos los claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
