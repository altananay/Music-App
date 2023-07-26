package com.atmosware.musicapp.business.dto.responses.update;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSongStyleResponse {
    private UUID id;
    private UUID songId;
    private String songName;
    private UUID styleId;
    private String styleName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}