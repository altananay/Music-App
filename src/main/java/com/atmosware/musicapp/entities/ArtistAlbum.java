package com.atmosware.musicapp.entities;

import com.atmosware.musicapp.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ArtistAlbum extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artistId;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album albumId;
}