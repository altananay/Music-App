package com.atmosware.musicapp.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAdminRequest {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}