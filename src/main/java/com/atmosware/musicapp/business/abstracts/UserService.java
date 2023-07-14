package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.business.dto.requests.create.CreateUserRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateUserRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateUserResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetUserResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateUserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<GetAllUsersResponse> getAll();
    GetUserResponse getById(UUID id);
    CreateUserResponse add(CreateUserRequest request);
    UpdateUserResponse update(UUID id, UpdateUserRequest request);
    void delete(UUID id);
}