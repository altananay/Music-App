package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.AlbumSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AlbumSongRepository extends JpaRepository<AlbumSong, UUID> {
    List<AlbumSong> getByAlbumId(UUID id);
}