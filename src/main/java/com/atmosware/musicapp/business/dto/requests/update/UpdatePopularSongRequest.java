package com.atmosware.musicapp.business.dto.requests.update;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePopularSongRequest {
    private UUID songId;
    private UUID artistId;
    private int favoriteCount;
}