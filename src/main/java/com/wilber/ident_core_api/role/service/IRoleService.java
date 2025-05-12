package com.wilber.ident_core_api.role.service;

import com.wilber.ident_core_api.role.entity.RoleEntity;

public interface IRoleService {

    RoleEntity findRoleByRoleName(String roleName);

}
