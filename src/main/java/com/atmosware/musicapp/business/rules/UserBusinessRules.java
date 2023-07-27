package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.UserRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {
  private final UserRepository repository;

  public void checkIfUserExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.User.NotExists);
  }
}