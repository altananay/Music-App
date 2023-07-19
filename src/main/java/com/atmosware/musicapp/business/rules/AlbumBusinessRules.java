package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AlbumBusinessRules {

    private final AlbumRepository repository;

    public void CheckIfAlbumExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Album bulunamadı");
    }
}