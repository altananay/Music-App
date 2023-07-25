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
public class UpdateUserFavoriteSongResponse {
    private UUID id;
    private UUID songId;
    private UUID userId;
    private String userUsername;
    private String songName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}