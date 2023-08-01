package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.business.dto.requests.create.CreateArtistSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateArtistSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateArtistSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllArtistSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetArtistSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateArtistSongResponse;

import java.util.List;
import java.util.UUID;

public interface ArtistSongService {
    List<GetAllArtistSongsResponse> getAll();
    List<GetAllArtistSongsResponse> getByArtistId(UUID id);
    GetArtistSongResponse getBySongId(UUID id);
    GetArtistSongResponse getById(UUID id);
    CreateArtistSongResponse add(CreateArtistSongRequest request);
    UpdateArtistSongResponse update(UUID id, UpdateArtistSongRequest request);
    void delete(UUID id);
}