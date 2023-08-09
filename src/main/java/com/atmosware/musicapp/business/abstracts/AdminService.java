package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.business.dto.requests.create.CreateAdminRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateAdminRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateAdminResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAdminResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllAdminsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateAdminResponse;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    List<GetAllAdminsResponse> getAll();
    GetAdminResponse getById(UUID id);
    UpdateAdminResponse update(UUID id, UpdateAdminRequest request);
    void delete(UUID id);
}