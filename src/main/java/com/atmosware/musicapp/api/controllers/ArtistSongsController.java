package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.ArtistSongService;
import com.atmosware.musicapp.business.dto.requests.create.CreateArtistSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateArtistSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateArtistSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllArtistSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetArtistSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateArtistSongResponse;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/artistSongs")
public class ArtistSongsController {

  private final ArtistSongService service;

  @GetMapping
  public List<GetAllArtistSongsResponse> getAll() {
    return service.getAll();
  }

  @GetMapping("/getAllByArtistId/{id}")
  public List<GetAllArtistSongsResponse> getAll(@PathVariable UUID id) {
    return service.getByArtistId(id);
  }

  @GetMapping("/getArtistSongBySongId/{id}")
  public GetArtistSongResponse getArtistSongBySongId(@PathVariable UUID id) {
    return service.getBySongId(id);
  }

  @GetMapping("/{id}")
  public GetArtistSongResponse getById(@PathVariable UUID id) {
    return service.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CreateArtistSongResponse add(@RequestBody CreateArtistSongRequest request) {
    return service.add(request);
  }

  @PutMapping("/{id}")
  public UpdateArtistSongResponse update(
      @PathVariable UUID id, @RequestBody UpdateArtistSongRequest request) {
    return service.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }
}
