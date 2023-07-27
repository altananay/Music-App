package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.SongRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SongBusinessRules {

  private final SongRepository repository;

  public void checkIfSongExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.Song.NotExists);
  }
}