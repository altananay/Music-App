package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.business.dto.requests.create.CreateStyleRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateStyleRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateStyleResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllStylesResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetStyleResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateStyleResponse;
import java.util.List;
import java.util.UUID;

public interface StyleService {
    List<GetAllStylesResponse> getAll();
    GetStyleResponse getById(UUID id);
    CreateStyleResponse add(CreateStyleRequest request);
    UpdateStyleResponse update(UUID id, UpdateStyleRequest request);
    void delete(UUID id);
}