package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.SongStyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongStyleBusinessRules {
    private final SongStyleRepository repository;

    public void checkIfSongStyleExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Müzik stili bulunamadı");
    }
}