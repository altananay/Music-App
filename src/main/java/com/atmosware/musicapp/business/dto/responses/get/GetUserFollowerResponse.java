package com.atmosware.musicapp.business.dto.responses.get;

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
public class GetUserFollowerResponse {
    private UUID id;
    private UUID userId;
    private String userUsername;
    private UUID followerId;
    private String follower;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
