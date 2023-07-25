package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.UserFavoriteSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserFavoriteSongRepository extends JpaRepository<UserFavoriteSong, UUID> {
    List<UserFavoriteSong> getByUserId(UUID id);
}