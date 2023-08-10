package com.atmosware.musicapp.business.dto.requests.update;

import com.atmosware.musicapp.common.constants.Messages;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = Messages.Validation.UUID)
    private UUID userId;
    @NotNull(message = Messages.Validation.UUID)
    private UUID songId;
}