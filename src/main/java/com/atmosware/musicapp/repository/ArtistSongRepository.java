package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.ArtistSong;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistSongRepository extends JpaRepository<ArtistSong, UUID> {
  List<ArtistSong> getByArtistId(UUID id);
  ArtistSong findFirstBySongId(UUID id);
  Boolean existsBySongId(UUID id);
}