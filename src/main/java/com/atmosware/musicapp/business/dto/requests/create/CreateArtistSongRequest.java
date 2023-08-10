package com.atmosware.musicapp.business.dto.requests.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateArtistSongRequest {
    @NotNull
    private UUID artistId;
    @NotNull
    private UUID songId;
}