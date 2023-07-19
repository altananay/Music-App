package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository repository;

    public void CheckIfUserExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Kullanıcı bulunamadı.");
    }
}