package com.atmosware.musicapp.business.dto.requests.create;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAlbumRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String year;
}