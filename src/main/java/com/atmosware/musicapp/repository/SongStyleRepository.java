package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.SongStyle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SongStyleRepository extends JpaRepository<SongStyle, UUID> {}