package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.ArtistAlbumRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArtistAlbumBusinessRules {

  private final ArtistAlbumRepository repository;

  public void checkIfArtistAlbumExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.ArtistAlbum.NOT_EXISTS);
  }
}