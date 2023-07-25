package com.atmosware.musicapp.entities;

import com.atmosware.musicapp.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "artists")
public class Artist extends BaseEntity {
    private String name;
}