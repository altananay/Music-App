package com.atmosware.musicapp.business.dto.requests.create;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePopularSongRequest {
    private UUID songId;
    private UUID artistId;
}