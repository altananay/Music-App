package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.UserFollower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserFollowerRepository extends JpaRepository<UserFollower, UUID> {
    List<UserFollower> getByUserId(UUID id);

    Boolean existsByUserIdAndFollowedUserId(UUID id, UUID followedUserId);
}
