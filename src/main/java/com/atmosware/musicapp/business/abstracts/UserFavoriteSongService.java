package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.business.dto.requests.create.CreateUserFavoriteSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateUserFavoriteSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateUserFavoriteSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersFavoriteSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetUserFavoriteSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateUserFavoriteSongResponse;
import java.util.List;
import java.util.UUID;

public interface UserFavoriteSongService {
    List<GetAllUsersFavoriteSongsResponse> getAll();
    List<GetAllUsersFavoriteSongsResponse> getByUserId(UUID userId);
    List<GetAllUsersFavoriteSongsResponse> getMutualSongsByUsersId(UUID firstUserId, UUID secondUserId);
    GetUserFavoriteSongResponse getById(UUID id);
    CreateUserFavoriteSongResponse add(CreateUserFavoriteSongRequest request);
    UpdateUserFavoriteSongResponse update(UUID id, UpdateUserFavoriteSongRequest request);
    void delete(UUID id);
}
