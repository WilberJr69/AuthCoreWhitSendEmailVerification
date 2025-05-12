package com.wilber.ident_core_api.role.repo;

import com.wilber.ident_core_api.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByRoleName(String roleName);

}
