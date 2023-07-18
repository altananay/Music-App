package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.PopularSong;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PopularSongRepository extends CrudRepository<PopularSong, UUID> {
}