package com.atmosware.musicapp.entities;

import com.atmosware.musicapp.entities.base.BaseEntity;
import com.atmosware.musicapp.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")
public class User extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}