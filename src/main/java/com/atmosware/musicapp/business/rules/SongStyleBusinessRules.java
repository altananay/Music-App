package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.SongStyleRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongStyleBusinessRules {
  private final SongStyleRepository repository;

  public void checkIfSongStyleExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.SongStyle.NotExists);
  }
}