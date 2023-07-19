package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.ArtistAlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistAlbumBusinessRules {

    private final ArtistAlbumRepository repository;

    public void CheckIfArtistAlbumExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Sanatçının albümü bulunamadı");
    }
}