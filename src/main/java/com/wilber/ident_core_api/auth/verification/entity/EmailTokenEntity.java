package com.wilber.ident_core_api.auth.verification.entity;

import com.wilber.ident_core_api.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "email_verification_tokens")
public class EmailTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "verification_token")
    private String verificationToken;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private UserEntity userEntity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "is_expired")
    private boolean isExpired;

    @Column(name = "used")
    private boolean isUsed;

}
