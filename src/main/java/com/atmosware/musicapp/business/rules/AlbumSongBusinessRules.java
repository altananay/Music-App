package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.AlbumSongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlbumSongBusinessRules {
    private final AlbumSongRepository repository;

    public void checkIfAlbumSongExists(UUID id)
    {
        if (!repository.existsById(id))
            throw new BusinessException(Messages.AlbumSong.NOT_EXISTS);
    }
}