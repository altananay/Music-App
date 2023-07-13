package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.ArtistSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistSongRepository extends JpaRepository<ArtistSong, UUID> {
}
