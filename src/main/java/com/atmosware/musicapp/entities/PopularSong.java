package com.atmosware.musicapp.entities;

import com.atmosware.musicapp.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("FavoriteSongs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PopularSong extends BaseEntity implements Serializable {
    private String name;
    private String favoriteCount;
}