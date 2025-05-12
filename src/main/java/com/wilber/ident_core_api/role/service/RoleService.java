package com.wilber.ident_core_api.role.service;

import com.wilber.ident_core_api.role.entity.RoleEntity;
import com.wilber.ident_core_api.role.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService{

    private final RoleRepo roleRepo;

    @Override
    public RoleEntity findRoleByRoleName(String roleName){

        return roleRepo.findByRoleName(roleName)
                .orElseThrow(()->new RuntimeException("El role con ese nombre no existe"));

    }


}
