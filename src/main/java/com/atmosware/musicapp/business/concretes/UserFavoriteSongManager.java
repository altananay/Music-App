package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.*;
import com.atmosware.musicapp.business.dto.requests.create.CreatePopularSongRequest;
import com.atmosware.musicapp.business.dto.requests.create.CreateUserFavoriteSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdatePopularSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateUserFavoriteSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateUserFavoriteSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersFavoriteMutualSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersFavoriteSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetUserFavoriteSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateUserFavoriteSongResponse;
import com.atmosware.musicapp.business.rules.*;
import com.atmosware.musicapp.common.utils.annotations.Logger;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.UserFavoriteSong;
import com.atmosware.musicapp.repository.UserFavoriteSongRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFavoriteSongManager implements UserFavoriteSongService {

  private final UserFavoriteSongRepository repository;
  private final UserFavoriteSongRules rules;
  private final UserBusinessRules userBusinessRules;
  private final ModelMapperService mapper;
  private final ArtistSongService artistSongService;
  private final ArtistSongBusinessRules artistSongBusinessRules;
  private final PopularSongService popularSongService;
  private final UserFollowerBusinessRules userFollowerBusinessRules;
  private final UserService userService;
  private final PopularSongBusinessRules popularSongBusinessRules;

  @Override
  @Logger
  public List<GetAllUsersFavoriteSongsResponse> getAll() {
    List<UserFavoriteSong> favoriteSongs = repository.findAll();
    return favoriteSongs.stream()
        .map(
            favoriteSong ->
                mapper.forResponse().map(favoriteSong, GetAllUsersFavoriteSongsResponse.class))
        .toList();
  }

  @Override
  @Logger
  public List<GetAllUsersFavoriteSongsResponse> getByUserId(UUID userId) {
    List<UserFavoriteSong> favoriteSongs = repository.getByUserId(userId);
    return favoriteSongs.stream()
        .map(
            favoriteSong ->
                mapper.forResponse().map(favoriteSong, GetAllUsersFavoriteSongsResponse.class))
        .toList();
  }

  @Override
  @Logger
  public List<GetAllUsersFavoriteMutualSongsResponse> getMutualSongsByUsersId(
      UUID firstUserId, UUID secondUserId) {
    userBusinessRules.checkIfUserExists(firstUserId);
    userBusinessRules.checkIfUserExists(secondUserId);
    userFollowerBusinessRules.checkIfUserFollowEachOther(firstUserId, secondUserId);
    List<GetAllUsersFavoriteSongsResponse> firstUserFavoriteSongs = getByUserId(firstUserId);
    List<GetAllUsersFavoriteSongsResponse> secondUserFavoriteSongs = getByUserId(secondUserId);
    List<GetAllUsersFavoriteSongsResponse> filter =
        firstUserFavoriteSongs.stream()
            .filter(
                first ->
                    secondUserFavoriteSongs.stream()
                        .anyMatch(second -> second.getSongName().equals(first.getSongName())))
            .toList();
    List<GetAllUsersFavoriteMutualSongsResponse> responses =
        filter.stream()
            .map(
                favoriteSong ->
                    mapper
                        .forResponse()
                        .map(favoriteSong, GetAllUsersFavoriteMutualSongsResponse.class))
            .toList();
    for (GetAllUsersFavoriteMutualSongsResponse response : responses) {
      response.setSecondUser(userService.getById(secondUserId).getUsername());
    }
    return responses;
  }

  @Override
  @Logger
  public GetUserFavoriteSongResponse getById(UUID id) {
    rules.checkIfUserFavoriteSongExists(id);
    UserFavoriteSong userFavoriteSong = repository.findById(id).orElseThrow();
    return mapper.forResponse().map(userFavoriteSong, GetUserFavoriteSongResponse.class);
  }

  @Override
  @Logger
  public CreateUserFavoriteSongResponse add(CreateUserFavoriteSongRequest request) {
    artistSongBusinessRules.checkIfArtistSongExistsBySongId(request.getSongId());
    UserFavoriteSong userFavoriteSong = mapper.forRequest().map(request, UserFavoriteSong.class);
    userFavoriteSong.setId(UUID.randomUUID());
    userFavoriteSong.setCreatedAt(LocalDateTime.now());

    CreatePopularSongRequest createPopularSongRequest = new CreatePopularSongRequest();
    createPopularSongRequest.setSongId(request.getSongId());

    createPopularSongRequest.setArtistId(
        artistSongService.getBySongId(request.getSongId()).getArtistId());

    popularSongService.add(createPopularSongRequest);
    UserFavoriteSong createdUserFavoriteSong = repository.save(userFavoriteSong);

    return mapper.forResponse().map(createdUserFavoriteSong, CreateUserFavoriteSongResponse.class);
  }

  @Override
  @Logger
  public UpdateUserFavoriteSongResponse update(UUID id, UpdateUserFavoriteSongRequest request) {
    artistSongBusinessRules.checkIfArtistSongExistsBySongId(request.getSongId());

    rules.checkIfUserFavoriteSongExists(id);
    UserFavoriteSong oldUserFavoriteSong =
        mapper.forRequest().map(getById(id), UserFavoriteSong.class);
    UserFavoriteSong newUserFavoriteSong = mapper.forRequest().map(request, UserFavoriteSong.class);
    newUserFavoriteSong.setId(id);
    newUserFavoriteSong.setUpdatedAt(LocalDateTime.now());
    newUserFavoriteSong.setCreatedAt(oldUserFavoriteSong.getCreatedAt());

    repository.save(newUserFavoriteSong);

    // old popular song operation started.
    UpdatePopularSongRequest oldUpdatePopularSongRequest = new UpdatePopularSongRequest();
    oldUpdatePopularSongRequest.setSongId(oldUserFavoriteSong.getSong().getId());
    oldUpdatePopularSongRequest.setArtistId(
        artistSongService.getBySongId(oldUserFavoriteSong.getSong().getId()).getArtistId());
    popularSongBusinessRules.checkIfPopularSongExistsBySongIdThrowException(
        oldUserFavoriteSong.getSong().getId());

    if (popularSongService.getBySongId(oldUserFavoriteSong.getSong().getId()).getFavoriteCount() - 1
        == 0)
      popularSongService.delete(
          popularSongService.getBySongId(oldUserFavoriteSong.getSong().getId()).getId());
    else {
      oldUpdatePopularSongRequest.setFavoriteCount(
              popularSongService.getBySongId(oldUserFavoriteSong.getSong().getId()).getFavoriteCount()
                      - 1);

      UUID popularSongId =
              popularSongService.getBySongId(oldUserFavoriteSong.getSong().getId()).getId();

      popularSongService.update(popularSongId, oldUpdatePopularSongRequest);
    }


    // old popular song operation finished.

    boolean result = popularSongBusinessRules.checkIfPopularSongExistsBySongId(request.getSongId());

    if (result) {
      UpdatePopularSongRequest newUpdatePopularSongRequest = new UpdatePopularSongRequest();
      newUpdatePopularSongRequest.setSongId(request.getSongId());
      newUpdatePopularSongRequest.setArtistId(
          artistSongService.getBySongId(request.getSongId()).getArtistId());
      newUpdatePopularSongRequest.setFavoriteCount(
          popularSongService.getBySongId(request.getSongId()).getFavoriteCount() + 1);
      popularSongService.update(
          popularSongService.getBySongId(request.getSongId()).getId(), newUpdatePopularSongRequest);
    } else {
      CreatePopularSongRequest createPopularSongRequest = new CreatePopularSongRequest();
      createPopularSongRequest.setSongId(request.getSongId());
      createPopularSongRequest.setArtistId(
          artistSongService.getBySongId(request.getSongId()).getArtistId());
      popularSongService.add(createPopularSongRequest);
    }

    UpdateUserFavoriteSongResponse response = mapper.forResponse().map(newUserFavoriteSong, UpdateUserFavoriteSongResponse.class);
    response.setUserUsername(userService.getById(request.getUserId()).getUsername());
    response.setSongName(artistSongService.getBySongId(request.getSongId()).getSongName());
    return response;
  }

  @Override
  @Logger
  public void delete(UUID id) {
    rules.checkIfUserFavoriteSongExists(id);
    repository.deleteById(id);
  }
}
