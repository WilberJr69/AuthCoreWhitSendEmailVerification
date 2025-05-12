package com.wilber.ident_core_api;

import com.wilber.ident_core_api.role.entity.RoleEntity;
import com.wilber.ident_core_api.role.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepo roleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void createDefaultRoles() {

        if (roleRepository.findByRoleName("ROLE_USER").isEmpty()) {
            RoleEntity userRole = new RoleEntity();
            userRole.setRoleName("ROLE_USER");
            roleRepository.save(userRole);
        }

        if (roleRepository.findByRoleName("ROLE_PRE_USER").isEmpty()) {
            RoleEntity adminRole = new RoleEntity();
            adminRole.setRoleName("ROLE_PRE_USER");
            roleRepository.save(adminRole);
        }

    }



}
