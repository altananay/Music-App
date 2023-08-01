package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.business.dto.requests.create.CreateSongStyleRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateSongStyleRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateSongStyleResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllSongsStylesResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetSongStyleResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateSongStyleResponse;

import java.util.List;
import java.util.UUID;

public interface SongStyleService {
    List<GetAllSongsStylesResponse> getAll();
    List<GetAllSongsStylesResponse> getAllByStyleId(UUID id);
    GetSongStyleResponse getById(UUID id);
    CreateSongStyleResponse add(CreateSongStyleRequest request);
    UpdateSongStyleResponse update(UUID id, UpdateSongStyleRequest request);
    void delete(UUID id);
}