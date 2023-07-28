package com.atmosware.musicapp.business.dto.responses.get;

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
public class GetAlbumSongResponse {
    private UUID id;
    private UUID albumId;
    private String albumName;
    private String albumYear;
    private UUID songId;
    private String songName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}