package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.Style;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StyleRepository extends JpaRepository<Style, UUID> {
    boolean existsByNameIgnoreCase(String name);
}