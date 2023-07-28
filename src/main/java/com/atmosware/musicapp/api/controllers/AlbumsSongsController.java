package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.AlbumSongService;
import com.atmosware.musicapp.business.dto.requests.create.CreateAlbumSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateAlbumSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateAlbumSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAlbumSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllAlbumsSongsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateAlbumSongResponse;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/albumsSongs")
public class AlbumsSongsController {
  private final AlbumSongService service;

  @GetMapping
  public List<GetAllAlbumsSongsResponse> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public GetAlbumSongResponse getById(@PathVariable UUID id) {
    return service.getById(id);
  }

  @GetMapping("/getAllByAlbumId/{id}")
  public List<GetAllAlbumsSongsResponse> getAllByAlbumId(@PathVariable UUID id) {
    return service.getAllByAlbumId(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CreateAlbumSongResponse add(@RequestBody CreateAlbumSongRequest request) {
    return service.add(request);
  }

  @PutMapping("/{id}")
  public UpdateAlbumSongResponse update(
      @PathVariable UUID id, @RequestBody UpdateAlbumSongRequest request) {
    return service.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }
}
