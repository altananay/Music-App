package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.UserFavoriteSongService;
import com.atmosware.musicapp.business.dto.requests.create.CreateUserFavoriteSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateUserFavoriteSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateUserFavoriteSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersFavoriteSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetUserFavoriteSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateUserFavoriteSongResponse;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/usersFavoriteSongs")
public class UsersFavoriteSongsController {
  private UserFavoriteSongService service;
  @GetMapping("/getAll")
  public List<GetAllUsersFavoriteSongsResponse> getAll() {
    return service.getAll();
  }

  @GetMapping("/getByUserId/{userId}")
  public List<GetAllUsersFavoriteSongsResponse> getByUserId(@PathVariable UUID userId) {
    return service.getByUserId(userId);
  }

  @GetMapping("/getMutualSongsByUsers")
  public List<GetAllUsersFavoriteSongsResponse> getAllByUserId(@RequestParam UUID firstUserId, @RequestParam UUID secondUserId) {
    return service.getMutualSongsByUsersId(firstUserId, secondUserId);
  }

  @GetMapping("/getById/{id}")
  public GetUserFavoriteSongResponse getById(@PathVariable UUID id) {
    return service.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CreateUserFavoriteSongResponse add(@RequestBody CreateUserFavoriteSongRequest request) {
    return service.add(request);
  }

  @PutMapping("/{id}")
  public UpdateUserFavoriteSongResponse update(
      @PathVariable UUID id, @RequestBody UpdateUserFavoriteSongRequest request) {
    return service.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }
}