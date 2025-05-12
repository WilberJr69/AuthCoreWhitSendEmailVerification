package com.wilber.ident_core_api.user.entity;

import com.wilber.ident_core_api.role.entity.RoleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "verified")
    private boolean verified;

    @ManyToOne
    @JoinColumn(name = "fk_role")
    private RoleEntity role;

}
