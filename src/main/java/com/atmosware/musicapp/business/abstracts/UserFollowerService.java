package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.business.dto.requests.create.CreateUserFollowerRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateUserFollowerRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateUserFollowerResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersFollowersResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetUserFollowerResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateUserFollowerResponse;
import java.util.List;
import java.util.UUID;

public interface UserFollowerService {
    List<GetAllUsersFollowersResponse> getAll();
    GetUserFollowerResponse getById(UUID id);
    List<GetAllUsersFollowersResponse> getByUserId(UUID id);
    CreateUserFollowerResponse add(CreateUserFollowerRequest request);
    UpdateUserFollowerResponse update(UUID id, UpdateUserFollowerRequest request);
    void delete(UUID id);
}