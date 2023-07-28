package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.SongStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SongStyleRepository extends JpaRepository<SongStyle, UUID> {
    List<SongStyle> getByStyleId(UUID id);

  @Query(
      value =
          "select s.\"name\", styles.\"name\", artists.\"name\" from song_styles ss, artist_song aSong, songs s, artists, styles where ss.song_id = aSong.song_id and s.id = ss.song_id and styles.id = ss.style_id and artists.id = aSong.artist_id and styles.id = :id",
      nativeQuery = true)
  List<Object> getByStyleIdWithDetails(UUID id);
}