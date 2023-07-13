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
public class UpdateArtistSongRequest {
    private UUID id;
    private UUID artistId;
    private UUID songId;
}
