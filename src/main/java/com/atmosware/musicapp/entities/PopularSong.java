package com.atmosware.musicapp.entities;

import com.atmosware.musicapp.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("FavoriteSongs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PopularSong extends BaseEntity {
    @Indexed
    private UUID songId;
    private String songName;
    @Indexed
    private UUID artistId;
    private String artistName;
    private Integer favoriteCount;
}