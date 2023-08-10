package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.core.utils.interfaces.MusicDto;
import com.atmosware.musicapp.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
  @Query(
      value = "select distinct s.id as song_id, s.\"name\" as song_name, sty.\"name\" as style_name, art.\"name\" as artist_name from song_styles as ss, songs as s, artists as art, styles as sty, artist_song as a_s where ss.song_id = s.id and ss.style_id = sty.id and art.id = a_s.artist_id and a_s.song_id = s.id", nativeQuery = true)
  List<MusicDto> getMusicWithDetails();

  boolean existsByNameIgnoreCase(String name);
}