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
public class GetAllAlbumsResponse {
    private UUID id;
    private String name;
    private String year;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}