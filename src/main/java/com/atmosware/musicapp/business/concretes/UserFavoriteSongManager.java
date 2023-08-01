package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.*;
import com.atmosware.musicapp.business.dto.requests.create.CreatePopularSongRequest;
import com.atmosware.musicapp.business.dto.requests.create.CreateUserFavoriteSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateUserFavoriteSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateUserFavoriteSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersFavoriteSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetUserFavoriteSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateUserFavoriteSongResponse;
import com.atmosware.musicapp.business.rules.*;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.UserFavoriteSong;
import com.atmosware.musicapp.repository.UserFavoriteSongRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
  public List<GetAllUsersFavoriteSongsResponse> getMutualSongsByUsersId(UUID firstUserId, UUID secondUserId) {
    userBusinessRules.checkIfUserExists(firstUserId);
    userBusinessRules.checkIfUserExists(secondUserId);
    userFollowerBusinessRules.checkIfUserFollowEachOther(firstUserId, secondUserId);
    List<GetAllUsersFavoriteSongsResponse> firstUserFavoriteSongs = getByUserId(firstUserId);
    List<GetAllUsersFavoriteSongsResponse> secondUserFavoriteSongs = getByUserId(secondUserId);
    List<GetAllUsersFavoriteSongsResponse> filter = firstUserFavoriteSongs.stream().filter(first -> secondUserFavoriteSongs.stream().anyMatch(second -> second.getSongName().equals(first.getSongName()))).collect(Collectors.toList());
    return filter;
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
    artistSongBusinessRules.checkIfArtistSongExistsBySongId(request.getSongId());
    UserFavoriteSong userFavoriteSong = mapper.forRequest().map(request, UserFavoriteSong.class);
    userFavoriteSong.setId(UUID.randomUUID());
    userFavoriteSong.setCreatedAt(LocalDateTime.now());

    // Popular song operations started.

    CreatePopularSongRequest createPopularSongRequest = new CreatePopularSongRequest();
    createPopularSongRequest.setSongId(request.getSongId());



    createPopularSongRequest.setArtistId(
        artistSongService.getBySongId(request.getSongId()).getArtistId());

    popularSongService.add(createPopularSongRequest);
    UserFavoriteSong createdUserFavoriteSong = repository.save(userFavoriteSong);
    // Popular song operations finished.
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
