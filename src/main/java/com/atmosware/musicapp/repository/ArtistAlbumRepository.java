package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.ArtistAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistAlbumRepository extends JpaRepository<ArtistAlbum, UUID> {
    ArtistAlbum findFirstByArtistId(UUID id);
    Boolean existsByArtistId(UUID id);
}
