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
    if (!repository.existsById(id)) throw new BusinessException(Messages.User.NOT_EXISTS);
  }

  public void checkIfUserExistsByEmail(String email)
  {
    if (!repository.existsByEmail(email))
      throw new BusinessException(Messages.User.NOT_EXISTS);
  }

  public void checkIfUserExistsByEmailAlternative(String email)
  {
    if (repository.existsByEmail(email))
      throw new BusinessException(Messages.User.USER_ALREADY_EXISTS_EMAIL);
  }

  public void checkIfUserExistsByUsername(String username)
  {
    if (repository.existsByUsernameIgnoreCase(username))
      throw new BusinessException(Messages.User.USER_ALREADY_EXISTS_USERNAME);
  }
}