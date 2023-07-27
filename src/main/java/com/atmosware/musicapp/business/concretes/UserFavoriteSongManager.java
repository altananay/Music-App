package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.UserFavoriteSongService;
import com.atmosware.musicapp.business.dto.requests.create.CreateUserFavoriteSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateUserFavoriteSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateUserFavoriteSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersFavoriteSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetUserFavoriteSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateUserFavoriteSongResponse;
import com.atmosware.musicapp.business.rules.UserFavoriteSongRules;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.UserFavoriteSong;
import com.atmosware.musicapp.repository.UserFavoriteSongRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserFavoriteSongManager implements UserFavoriteSongService {

  private final UserFavoriteSongRepository repository;
  private final UserFavoriteSongRules rules;
  private final ModelMapperService mapper;

  @Override
  public List<GetAllUsersFavoriteSongsResponse> getAll() {
    List<UserFavoriteSong> favoriteSongs = repository.findAll();
    List<GetAllUsersFavoriteSongsResponse> responses =
        favoriteSongs.stream()
            .map(
                favoriteSong ->
                    mapper.forResponse().map(favoriteSong, GetAllUsersFavoriteSongsResponse.class))
            .toList();
    return responses;
  }

  @Override
  public List<GetAllUsersFavoriteSongsResponse> getByUserId(UUID userId) {
    List<UserFavoriteSong> favoriteSongs = repository.getByUserId(userId);
    List<GetAllUsersFavoriteSongsResponse> responses =
        favoriteSongs.stream()
            .map(
                favoriteSong ->
                    mapper.forResponse().map(favoriteSong, GetAllUsersFavoriteSongsResponse.class))
            .toList();
    return responses;
  }

  @Override
  public GetUserFavoriteSongResponse getById(UUID id) {
    rules.checkIfUserFavoriteSongExists(id);
    UserFavoriteSong userFavoriteSong = repository.findById(id).orElseThrow();
    GetUserFavoriteSongResponse response =
        mapper.forResponse().map(userFavoriteSong, GetUserFavoriteSongResponse.class);
    return response;
  }

  @Override
  public CreateUserFavoriteSongResponse add(CreateUserFavoriteSongRequest request) {
    UserFavoriteSong userFavoriteSong = mapper.forRequest().map(request, UserFavoriteSong.class);
    userFavoriteSong.setId(UUID.randomUUID());
    userFavoriteSong.setCreatedAt(LocalDateTime.now());
    UserFavoriteSong createdUserFavoriteSong = repository.save(userFavoriteSong);
    CreateUserFavoriteSongResponse response =
        mapper.forResponse().map(createdUserFavoriteSong, CreateUserFavoriteSongResponse.class);
    return response;
  }

  @Override
  public UpdateUserFavoriteSongResponse update(UUID id, UpdateUserFavoriteSongRequest request) {
    rules.checkIfUserFavoriteSongExists(id);
    UserFavoriteSong oldUserFavoriteSong =
        mapper.forRequest().map(getById(id), UserFavoriteSong.class);
    UserFavoriteSong newUserFavoriteSong = mapper.forRequest().map(request, UserFavoriteSong.class);
    newUserFavoriteSong.setId(id);
    newUserFavoriteSong.setUpdatedAt(LocalDateTime.now());
    newUserFavoriteSong.setCreatedAt(oldUserFavoriteSong.getCreatedAt());
    repository.save(newUserFavoriteSong);
    UpdateUserFavoriteSongResponse response =
        mapper.forResponse().map(newUserFavoriteSong, UpdateUserFavoriteSongResponse.class);
    return response;
  }

  @Override
  public void delete(UUID id) {
    rules.checkIfUserFavoriteSongExists(id);
    repository.deleteById(id);
  }
}
