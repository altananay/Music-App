package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.UserFavoriteSongRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserFavoriteSongRules {
  private final UserFavoriteSongRepository repository;

  public void checkIfUserFavoriteSongExists(UUID id) {
    if (!repository.existsById(id))
      throw new BusinessException(Messages.UserFavoriteSong.NOT_EXISTS);
  }
}