package com.atmosware.musicapp.business.dto.responses.create;

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
public class CreateArtistSongResponse {
    private UUID id;
    private UUID artistId;
    private String artistName;
    private UUID songId;
    private String songName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}