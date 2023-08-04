package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.AdminRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminBusinessRules {

  private final AdminRepository repository;

  public void checkIfAdminExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.Admin.NOT_EXISTS);
  }
}