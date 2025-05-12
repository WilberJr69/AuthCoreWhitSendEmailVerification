package com.wilber.ident_core_api.user.map;

import com.wilber.ident_core_api.auth.dto.RegisterRequest;
import com.wilber.ident_core_api.role.service.IRoleService;
import com.wilber.ident_core_api.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMap {

    private final IRoleService roleService;

    public UserEntity toUserEntity(RegisterRequest registerRequest){

        UserEntity user = new UserEntity();
        user.setUsername(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setVerified(false);

        return user;
    }





}
