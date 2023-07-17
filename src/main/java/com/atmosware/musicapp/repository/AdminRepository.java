package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {

    Admin findByEmail(String email);
    @Query("SELECT a.username from Admin a where a.username =?1")
    String findByUsername(String username);
    Admin getByUsername(String username);
}