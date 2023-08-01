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
public class CreatePopularSongResponse {
    private UUID id;
    private UUID songId;
    private String songName;
    private UUID artistId;
    private String artistName;
    private String favoriteCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}