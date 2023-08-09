package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.business.dto.requests.create.CreatePopularSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdatePopularSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreatePopularSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllPopularSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetPopularSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdatePopularSongResponse;
import java.util.List;
import java.util.UUID;

public interface PopularSongService {
    CreatePopularSongResponse add(CreatePopularSongRequest request);
    GetPopularSongResponse getById(UUID id);

    GetPopularSongResponse getBySongId(UUID id);

    UpdatePopularSongResponse update(UUID id, UpdatePopularSongRequest request);
    void delete(UUID id);
    List<GetAllPopularSongsResponse> getAll();
    List<GetAllPopularSongsResponse> getAllByPagination(int pageNumber, int pageSize);
}
