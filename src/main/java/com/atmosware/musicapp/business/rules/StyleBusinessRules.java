package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.StyleRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StyleBusinessRules {
  private final StyleRepository repository;

  public void checkIfStyleExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.Style.NOT_EXISTS);
  }
}