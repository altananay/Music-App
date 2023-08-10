package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.ArtistService;
import com.atmosware.musicapp.business.abstracts.PopularSongService;
import com.atmosware.musicapp.business.abstracts.SongService;
import com.atmosware.musicapp.business.dto.requests.create.CreatePopularSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdatePopularSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreatePopularSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllPopularSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetPopularSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdatePopularSongResponse;
import com.atmosware.musicapp.business.rules.PopularSongBusinessRules;
import com.atmosware.musicapp.common.utils.annotations.Logger;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.Artist;
import com.atmosware.musicapp.entities.PopularSong;
import com.atmosware.musicapp.entities.Song;
import com.atmosware.musicapp.repository.PopularSongRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PopularSongManager implements PopularSongService {

  private final PopularSongRepository repository;
  private final ModelMapperService mapperService;
  private final PopularSongBusinessRules rules;
  private final SongService songService;
  private final ArtistService artistService;

  @Override
  @Logger
  public CreatePopularSongResponse add(CreatePopularSongRequest request) {
    Song song =
        mapperService.forRequest().map(songService.getById(request.getSongId()), Song.class);
    Artist artist =
        mapperService.forRequest().map(artistService.getById(request.getArtistId()), Artist.class);
    PopularSong popularSong = mapperService.forRequest().map(request, PopularSong.class);
    popularSong.setId(UUID.randomUUID());
    popularSong.setCreatedAt(LocalDateTime.now());
    popularSong.setSongName(song.getName());
    popularSong.setArtistName(artist.getName());
    if (repository.existsBySongId(popularSong.getSongId())) {
      var oldSong = repository.getBySongId(popularSong.getSongId());
      UpdatePopularSongRequest updatePopularSongRequest =
          mapperService.forRequest().map(popularSong, UpdatePopularSongRequest.class);
      updatePopularSongRequest.setFavoriteCount(oldSong.getFavoriteCount() + 1);
      update(oldSong.getId(), updatePopularSongRequest);
    } else {
      popularSong.setFavoriteCount(1);
      repository.save(popularSong);
    }
    CreatePopularSongResponse response =
        mapperService.forResponse().map(popularSong, CreatePopularSongResponse.class);
    return response;
  }

  @Override
  @Logger
  public GetPopularSongResponse getById(UUID id) {
    rules.checkIfPopularSongExists(id);
    PopularSong popularSong = repository.findById(id).orElseThrow();
    GetPopularSongResponse response =
        mapperService.forResponse().map(popularSong, GetPopularSongResponse.class);
    return response;
  }

  @Override
  public GetPopularSongResponse getBySongId(UUID id) {
    PopularSong popularSong = repository.getBySongId(id);
    GetPopularSongResponse response =
        mapperService.forResponse().map(popularSong, GetPopularSongResponse.class);
    return response;
  }

  @Override
  @Logger
  public UpdatePopularSongResponse update(UUID id, UpdatePopularSongRequest request) {
    rules.checkIfPopularSongExists(id);
    Song song =
        mapperService.forRequest().map(songService.getById(request.getSongId()), Song.class);
    Artist artist =
        mapperService.forRequest().map(artistService.getById(request.getArtistId()), Artist.class);
    PopularSong oldPopularSong = mapperService.forRequest().map(getById(id), PopularSong.class);

    PopularSong popularSong = mapperService.forRequest().map(request, PopularSong.class);
    popularSong.setId(id);
    popularSong.setFavoriteCount(request.getFavoriteCount());
    popularSong.setCreatedAt(oldPopularSong.getCreatedAt());
    popularSong.setSongName(song.getName());
    popularSong.setArtistName(artist.getName());
    popularSong.setUpdatedAt(LocalDateTime.now());
    repository.save(popularSong);

    UpdatePopularSongResponse response =
        mapperService.forResponse().map(popularSong, UpdatePopularSongResponse.class);
    return response;
  }

  @Override
  @Logger
  public void delete(UUID id) {
    rules.checkIfPopularSongExists(id);
    repository.deleteById(id);
  }

  @Override
  @Logger
  public List<GetAllPopularSongsResponse> getAll() {
    List<PopularSong> popularSongs = repository.findAll();
    List<GetAllPopularSongsResponse> responses =
        popularSongs.stream()
            .map(
                popularSong ->
                    mapperService.forResponse().map(popularSong, GetAllPopularSongsResponse.class))
            .toList();
    return responses;
  }

  @Override
  @Logger
  public List<GetAllPopularSongsResponse> getAllByPagination(int pageNumber, int pageSize) {
    List<PopularSong> popularSongs =
        repository.findAll().stream()
            .skip((long) (pageNumber - 1) * pageSize)
            .limit(pageSize)
            .toList();
    List<GetAllPopularSongsResponse> responses =
        popularSongs.stream()
            .map(
                popularSong ->
                    mapperService.forResponse().map(popularSong, GetAllPopularSongsResponse.class))
            .toList();
    return responses;
  }

  @Override
  public List<GetAllPopularSongsResponse> getAllByArtistId(UUID id) {
    List<PopularSong> popularSongs = repository.getAllByArtistId(id);
    return popularSongs.stream().map(popularSong -> mapperService.forResponse().map(popularSong, GetAllPopularSongsResponse.class)).toList();
  }
}
