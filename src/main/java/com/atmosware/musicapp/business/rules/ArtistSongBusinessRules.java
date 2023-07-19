package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.ArtistSongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistSongBusinessRules {

    private final ArtistSongRepository repository;

    public void CheckIfArtistSongExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Sanatçının müziği bulunamadı");
    }
}