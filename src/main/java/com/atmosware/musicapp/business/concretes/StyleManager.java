package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.StyleService;
import com.atmosware.musicapp.business.dto.requests.create.CreateStyleRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateStyleRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateStyleResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllStylesResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetStyleResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateStyleResponse;
import com.atmosware.musicapp.business.rules.StyleBusinessRules;
import com.atmosware.musicapp.common.utils.annotations.Logger;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.Style;
import com.atmosware.musicapp.repository.StyleRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StyleManager implements StyleService {

  private final StyleRepository repository;
  private final StyleBusinessRules rules;
  private final ModelMapperService mapper;

  @Override
  @Logger
  public List<GetAllStylesResponse> getAll() {
    List<Style> styles = repository.findAll();
    List<GetAllStylesResponse> responses =
        styles.stream()
            .map(style -> mapper.forResponse().map(style, GetAllStylesResponse.class))
            .toList();
    return responses;
  }

  @Override
  @Logger
  public GetStyleResponse getById(UUID id) {
    rules.checkIfStyleExists(id);
    Style style = repository.findById(id).orElseThrow();
    GetStyleResponse response = mapper.forResponse().map(style, GetStyleResponse.class);
    return response;
  }

  @Override
  @Logger
  public CreateStyleResponse add(CreateStyleRequest request) {
    Style style = mapper.forRequest().map(request, Style.class);
    style.setId(UUID.randomUUID());
    style.setCreatedAt(LocalDateTime.now());
    Style createdStyle = repository.save(style);
    CreateStyleResponse response =
        mapper.forResponse().map(createdStyle, CreateStyleResponse.class);
    return response;
  }

  @Override
  @Logger
  public UpdateStyleResponse update(UUID id, UpdateStyleRequest request) {
    rules.checkIfStyleExists(id);
    Style oldStyle = mapper.forRequest().map(getById(id), Style.class);
    Style style = mapper.forRequest().map(request, Style.class);
    style.setId(id);
    style.setCreatedAt(oldStyle.getCreatedAt());
    style.setUpdatedAt(LocalDateTime.now());
    repository.save(style);
    UpdateStyleResponse response = mapper.forResponse().map(style, UpdateStyleResponse.class);
    return response;
  }

  @Override
  @Logger
  public void delete(UUID id) {
    rules.checkIfStyleExists(id);
    repository.deleteById(id);
  }
}