package com.wilber.ident_core_api.user.repo;

import com.wilber.ident_core_api.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByEmail(String email);

}
