package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.ArtistRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArtistBusinessRules {

    private final ArtistRepository repository;

    public void CheckIfArtistExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Sanatçı bulunamadı");
    }
}