package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.business.dto.requests.create.CreateSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateSongResponse;

import java.util.List;
import java.util.UUID;

public interface SongService {
    List<GetAllSongsResponse> getAll();
    GetSongResponse getById(UUID id);
    CreateSongResponse add(CreateSongRequest request);
    UpdateSongResponse update(UUID id, UpdateSongRequest request);
    void delete(UUID id);
}