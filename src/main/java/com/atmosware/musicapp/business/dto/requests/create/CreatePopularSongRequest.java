package com.atmosware.musicapp.business.dto.requests.create;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePopularSongRequest {
    @NotNull
    private UUID songId;
    @NotNull
    private UUID artistId;
}