package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.business.dto.requests.create.CreateAlbumSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateAlbumSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateAlbumSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAlbumSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllAlbumsSongsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateAlbumSongResponse;

import java.util.List;
import java.util.UUID;

public interface AlbumSongService {
    List<GetAllAlbumsSongsResponse> getAll();
    List<GetAllAlbumsSongsResponse> getAllByAlbumId(UUID id);
    GetAlbumSongResponse getById(UUID id);
    CreateAlbumSongResponse add(CreateAlbumSongRequest request);
    UpdateAlbumSongResponse update(UUID id, UpdateAlbumSongRequest request);
    void delete(UUID id);
}