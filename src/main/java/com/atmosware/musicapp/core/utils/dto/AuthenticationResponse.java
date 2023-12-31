package com.atmosware.musicapp.core.utils.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private String token;
    private String result;
}