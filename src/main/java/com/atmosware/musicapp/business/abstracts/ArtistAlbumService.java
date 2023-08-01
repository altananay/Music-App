package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.business.dto.requests.create.CreateArtistAlbumRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateArtistAlbumRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateArtistAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllArtistAlbumsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetArtistAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateArtistAlbumResponse;

import java.util.List;
import java.util.UUID;

public interface ArtistAlbumService {
    List<GetAllArtistAlbumsResponse> getAll();
    GetArtistAlbumResponse getById(UUID id);
    GetArtistAlbumResponse getFirstByArtistId(UUID id);
    CreateArtistAlbumResponse add(CreateArtistAlbumRequest request);
    UpdateArtistAlbumResponse update(UUID id, UpdateArtistAlbumRequest request);
    void delete(UUID id);
}
