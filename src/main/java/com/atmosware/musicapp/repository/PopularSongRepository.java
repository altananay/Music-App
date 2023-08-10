package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.PopularSong;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopularSongRepository extends JpaRepository<PopularSong, UUID> {
    PopularSong getBySongId(UUID id);
    Boolean existsBySongId(UUID id);
    List<PopularSong> getAllByArtistId(UUID id);
}