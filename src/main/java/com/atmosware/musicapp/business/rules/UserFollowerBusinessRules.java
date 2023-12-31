package com.atmosware.musicapp.business.rules;

import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import com.atmosware.musicapp.repository.UserFollowerRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFollowerBusinessRules {

  private final UserFollowerRepository repository;

  public void checkIfUserFollowerExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.UserFollower.NOT_EXISTS);
  }

  public void checkIfUserFollowEachOther(UUID firstUserId, UUID secondUserId)
  {
    if (!(repository.existsByUserIdAndFollowedUserId(firstUserId, secondUserId) && repository.existsByUserIdAndFollowedUserId(secondUserId, firstUserId)))
      throw new BusinessException(Messages.UserFollower.USERS_NOT_FOLLOWING_EACH_OTHER);
  }

  public void checkIfUserFollow(UUID firstUserId, UUID secondUserId)
  {
    if (repository.existsByUserIdAndFollowedUserId(firstUserId, secondUserId))
      throw new BusinessException(Messages.UserFollower.USER_ALREADY_FOLLOW);
  }
}