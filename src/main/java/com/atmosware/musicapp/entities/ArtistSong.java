package com.atmosware.musicapp.entities;

import com.atmosware.musicapp.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ArtistSong extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artistId;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song songId;
}