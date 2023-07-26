package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.UserFollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFollowerBusinessRules {

    private final UserFollowerRepository repository;

    public void checkIfUserFollowerExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Takipçi bulunamadı");
    }
}