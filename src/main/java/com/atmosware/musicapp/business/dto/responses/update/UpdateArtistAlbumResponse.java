package com.atmosware.musicapp.business.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArtistAlbumResponse {
    private UUID id;
    private UUID artistId;
    private UUID albumId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
