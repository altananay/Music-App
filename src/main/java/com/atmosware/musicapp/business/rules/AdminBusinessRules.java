package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminBusinessRules {

    private final AdminRepository repository;

    public void CheckIfAdminExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new RuntimeException("Admin bulunamadı");
    }
}