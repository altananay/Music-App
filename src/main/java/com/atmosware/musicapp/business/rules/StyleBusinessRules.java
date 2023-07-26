package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StyleBusinessRules {
    private final StyleRepository repository;

    public void checkIfStyleExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Müzik tarzı bulunamadı");
    }
}