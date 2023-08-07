package com.atmosware.musicapp.repository;

import com.atmosware.musicapp.entities.Admin;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Admin findByEmail(String email);
}