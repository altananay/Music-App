package com.atmosware.musicapp.business.dto.requests.update;

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
public class UpdateAlbumSongRequest {
    @NotNull
    private UUID albumId;
    @NotNull
    private UUID songId;
}