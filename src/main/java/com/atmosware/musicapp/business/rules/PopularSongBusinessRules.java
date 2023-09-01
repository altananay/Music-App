package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.PopularSongRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PopularSongBusinessRules {

  private final PopularSongRepository repository;

  public void checkIfPopularSongExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.PopularSong.NOT_EXISTS);
  }

  public boolean checkIfPopularSongExistsBySongId(UUID id)
  {
    return repository.existsBySongId(id);
  }

  public void checkIfPopularSongExistsBySongIdThrowException(UUID id)
  {
    if (!repository.existsBySongId(id))
      throw new BusinessException(Messages.PopularSong.MUSIC_NOT_EXISTS);
  }
}
