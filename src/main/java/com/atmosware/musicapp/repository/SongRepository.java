package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
}
