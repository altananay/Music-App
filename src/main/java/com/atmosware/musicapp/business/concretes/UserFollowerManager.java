package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.UserFollowerService;
import com.atmosware.musicapp.business.abstracts.UserService;
import com.atmosware.musicapp.business.dto.requests.create.CreateUserFollowerRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateUserFollowerRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateUserFollowerResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersFollowersResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetUserFollowerResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateUserFollowerResponse;
import com.atmosware.musicapp.business.rules.UserFollowerBusinessRules;
import com.atmosware.musicapp.common.utils.annotations.Logger;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.UserFollower;
import com.atmosware.musicapp.repository.UserFollowerRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFollowerManager implements UserFollowerService {

  private final UserFollowerBusinessRules rules;
  private final UserService userService;
  private final UserFollowerRepository repository;
  private final ModelMapperService mapper;

  @Override
  @Logger
  public List<GetAllUsersFollowersResponse> getAll() {
    List<UserFollower> userFollowers = repository.findAll();
    List<GetAllUsersFollowersResponse> responses =
        userFollowers.stream()
            .map(
                userFollower ->
                    mapper.forResponse().map(userFollower, GetAllUsersFollowersResponse.class))
            .toList();
    for (var response : responses) {
      response.setFollowerUsername(userService.getById(response.getFollowedUserId()).getUsername());
    }
    return responses;
  }

  @Override
  @Logger
  public GetUserFollowerResponse getById(UUID id) {
    rules.checkIfUserFollowerExists(id);
    UserFollower userFollower = repository.findById(id).orElseThrow();
    GetUserFollowerResponse response =
        mapper.forResponse().map(userFollower, GetUserFollowerResponse.class);
    response.setFollowerUsername(userService.getById(response.getFollowedUserId()).getUsername());
    return response;
  }

  @Override
  @Logger
  public List<GetAllUsersFollowersResponse> getByUserId(UUID id) {
    List<UserFollower> userFollowers = repository.getByUserId(id);
    List<GetAllUsersFollowersResponse> responses =
        userFollowers.stream()
            .map(
                userFollower ->
                    mapper.forResponse().map(userFollower, GetAllUsersFollowersResponse.class))
            .toList();
    for (var response : responses) {
      response.setFollowerUsername(userService.getById(response.getFollowedUserId()).getUsername());
    }
    return responses;
  }

  @Override
  @Logger
  public CreateUserFollowerResponse add(CreateUserFollowerRequest request) {
    rules.checkIfUserFollow(request.getUserId(), request.getFollowedUserId());
    UserFollower userFollower = mapper.forRequest().map(request, UserFollower.class);
    userFollower.setId(UUID.randomUUID());
    userFollower.setCreatedAt(LocalDateTime.now());
    UserFollower createdUserFollower = repository.save(userFollower);
    CreateUserFollowerResponse response =
        mapper.forResponse().map(createdUserFollower, CreateUserFollowerResponse.class);
    response.setFollowerUsername(userService.getById(request.getFollowedUserId()).getUsername());
    return response;
  }

  @Override
  @Logger
  public UpdateUserFollowerResponse update(UUID id, UpdateUserFollowerRequest request) {
    rules.checkIfUserFollowerExists(id);
    rules.checkIfUserFollow(request.getUserId(), request.getFollowedUserId());
    UserFollower oldUserFollower = mapper.forRequest().map(getById(id), UserFollower.class);
    UserFollower userFollower = mapper.forRequest().map(request, UserFollower.class);
    userFollower.setId(id);
    userFollower.setCreatedAt(oldUserFollower.getCreatedAt());
    userFollower.setUpdatedAt(LocalDateTime.now());
    UserFollower updatedUserFollower = repository.save(userFollower);
    UpdateUserFollowerResponse response =
        mapper.forResponse().map(updatedUserFollower, UpdateUserFollowerResponse.class);
    response.setFollowerUsername(userService.getById(request.getFollowedUserId()).getUsername());
    return response;
  }

  @Override
  @Logger
  public void delete(UUID id) {
    rules.checkIfUserFollowerExists(id);
    repository.deleteById(id);
  }
}