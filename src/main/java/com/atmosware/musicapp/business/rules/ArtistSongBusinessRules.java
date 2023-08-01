package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.ArtistSongRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArtistSongBusinessRules {

  private final ArtistSongRepository repository;

  public void checkIfArtistSongExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.ArtistSong.NotExists);
  }

  public void checkIfArtistSongExistsBySongId(UUID id)
  {
    if (!repository.existsBySongId(id)) throw new BusinessException("Muzigin sanatçı bilgisi yok");
  }
}
