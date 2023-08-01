package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.UserFavoriteSong;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserFavoriteSongRepository extends JpaRepository<UserFavoriteSong, UUID> {
    List<UserFavoriteSong> getByUserId(UUID id);
}