package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.SongService;
import com.atmosware.musicapp.business.abstracts.SongStyleService;
import com.atmosware.musicapp.business.abstracts.StyleService;
import com.atmosware.musicapp.business.dto.requests.create.CreateSongStyleRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateSongStyleRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateSongStyleResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllSongsStylesResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetSongStyleResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateSongStyleResponse;
import com.atmosware.musicapp.business.rules.SongStyleBusinessRules;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.SongStyle;
import com.atmosware.musicapp.repository.SongStyleRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SongStyleManager implements SongStyleService {

  private final SongStyleRepository repository;
  private final SongStyleBusinessRules rules;
  private final ModelMapperService mapper;
  private final SongService songService;
  private final StyleService styleService;

  @Override
  public List<GetAllSongsStylesResponse> getAll() {
    List<SongStyle> songStyles = repository.findAll();
    List<GetAllSongsStylesResponse> responses =
            songStyles.stream()
            .map(songStyle -> mapper.forResponse().map(songStyle, GetAllSongsStylesResponse.class))
            .toList();
    return responses;
  }

  @Override
  public GetSongStyleResponse getById(UUID id) {
    rules.checkIfSongStyleExists(id);
    SongStyle songStyle = repository.findById(id).orElseThrow();
    GetSongStyleResponse response = mapper.forResponse().map(songStyle, GetSongStyleResponse.class);
    return response;
  }

  @Override
  public CreateSongStyleResponse add(CreateSongStyleRequest request) {
    SongStyle songStyle = mapper.forRequest().map(request, SongStyle.class);
    songStyle.setId(UUID.randomUUID());
    songStyle.setCreatedAt(LocalDateTime.now());
    SongStyle createdSongStyle = repository.save(songStyle);
    CreateSongStyleResponse response =
        mapper.forResponse().map(createdSongStyle, CreateSongStyleResponse.class);
    return response;
  }

  @Override
  public UpdateSongStyleResponse update(UUID id, UpdateSongStyleRequest request) {
    rules.checkIfSongStyleExists(id);
    SongStyle oldSongStyle = mapper.forRequest().map(getById(id), SongStyle.class);
    SongStyle songStyle = mapper.forRequest().map(request, SongStyle.class);
    songStyle.setId(id);
    songStyle.setCreatedAt(oldSongStyle.getCreatedAt());
    songStyle.setUpdatedAt(LocalDateTime.now());
    repository.save(songStyle);
    UpdateSongStyleResponse response =
        mapper.forResponse().map(songStyle, UpdateSongStyleResponse.class);
    response.setSongName(songService.getById(response.getSongId()).getName());
    response.setStyleName(styleService.getById(response.getStyleId()).getName());
    return response;
  }

  @Override
  public void delete(UUID id) {
    rules.checkIfSongStyleExists(id);
    repository.deleteById(id);
  }
}
