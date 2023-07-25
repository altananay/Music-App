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
public class UpdateUserFavoriteSongRequest {
    private UUID id;
    private UUID userId;
    private UUID songId;
}