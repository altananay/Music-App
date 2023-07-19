package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SongBusinessRules {

    private final SongRepository repository;

    public void CheckIfSongExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Şarkı bulunamadı");
    }
}