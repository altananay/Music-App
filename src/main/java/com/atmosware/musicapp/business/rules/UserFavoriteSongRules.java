package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.UserFavoriteSongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserFavoriteSongRules {
    private final UserFavoriteSongRepository repository;

    public void CheckIfUserFavoriteSongExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Kullanıcının favori müziği yok");
    }
}
