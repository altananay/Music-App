package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.SongStyleService;
import com.atmosware.musicapp.business.dto.requests.create.CreateSongStyleRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateSongStyleRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateSongStyleResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllSongsStylesResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetSongStyleResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateSongStyleResponse;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/songsStyles")
public class SongsStylesController {

  private final SongStyleService service;

  @GetMapping
  public List<GetAllSongsStylesResponse> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public GetSongStyleResponse getById(@PathVariable UUID id) {
    return service.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CreateSongStyleResponse add(@RequestBody CreateSongStyleRequest request) {
    return service.add(request);
  }

  @PutMapping("/{id}")
  public UpdateSongStyleResponse update(
      @PathVariable UUID id, @RequestBody UpdateSongStyleRequest request) {
    return service.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }
}