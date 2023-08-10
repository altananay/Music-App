package com.atmosware.musicapp.business.dto.requests.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserFavoriteSongRequest {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID songId;
}