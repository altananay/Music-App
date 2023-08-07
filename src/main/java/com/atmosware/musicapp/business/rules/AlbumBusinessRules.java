package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.AlbumRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AlbumBusinessRules {

  private final AlbumRepository repository;

  public void checkIfAlbumExists(UUID id) {
    log.error("test");
    if (!repository.existsById(id)) throw new BusinessException(Messages.Album.NOT_EXISTS);
  }
}