package com.wilber.ident_core_api.auth.verification.repo;

import com.wilber.ident_core_api.auth.verification.entity.EmailTokenEntity;
import com.wilber.ident_core_api.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmailTokenRepo extends JpaRepository<EmailTokenEntity,Integer>{

    Optional<EmailTokenEntity> findByUserEntity(UserEntity userEntity);

    @Query("""
        SELECT l FROM EmailTokenEntity l
        WHERE l.verificationToken = :token
        AND l.isExpired = false
        AND l.isUsed = false
        """)
    Optional<EmailTokenEntity> findValidVerificationToken(@Param("token") String token);

    @Query(""" 
            SELECT l FROM EmailTokenEntity l
            WHERE l.expiresAt < :now
            AND l.isExpired = false
            AND l.isUsed = false
            """)
    List<EmailTokenEntity> findTokensExpired(@Param("now")LocalDateTime now);

}
