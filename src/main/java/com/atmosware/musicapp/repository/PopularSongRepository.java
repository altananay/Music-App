package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.PopularSong;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PopularSongRepository extends JpaRepository<PopularSong, UUID>, PagingAndSortingRepository<PopularSong, UUID> {
    PopularSong getBySongId(UUID id);
    Boolean existsBySongId(UUID id);
    List<PopularSong> findAll(Pageable pageable);
}