package com.atmosware.musicapp.business.dto.requests.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateArtistAlbumRequest {
    @NotNull
    private UUID artistId;
    @NotNull
    private UUID albumId;
}