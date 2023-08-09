package com.atmosware.musicapp.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}