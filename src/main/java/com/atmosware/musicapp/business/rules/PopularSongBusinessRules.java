package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.PopularSongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PopularSongBusinessRules {

    private final PopularSongRepository repository;

    public void CheckIfPopularSongExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Popular muzik bulunamadı");
    }
}