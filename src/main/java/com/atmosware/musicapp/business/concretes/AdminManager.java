package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.AdminService;
import com.atmosware.musicapp.business.dto.requests.create.CreateAdminRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateAdminRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateAdminResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAdminResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllAdminsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateAdminResponse;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.Admin;
import com.atmosware.musicapp.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminManager implements AdminService {
    private final AdminRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllAdminsResponse> getAll() {
        List<Admin> admins = repository.findAll();
        List<GetAllAdminsResponse> responses = admins.stream().map(admin -> mapper.forResponse().map(admin, GetAllAdminsResponse.class)).toList();
        return responses;
    }

    @Override
    public GetAdminResponse getById(UUID id) {
        Admin admin = repository.findById(id).orElseThrow();
        GetAdminResponse response = mapper.forResponse().map(admin, GetAdminResponse.class);
        return response;
    }

    @Override
    public CreateAdminResponse add(CreateAdminRequest request) {
        Admin admin = mapper.forRequest().map(request, Admin.class);
        admin.setId(UUID.randomUUID());
        Admin createdAdmin = repository.save(admin);
        CreateAdminResponse response = mapper.forResponse().map(createdAdmin, CreateAdminResponse.class);
        return response;
    }

    @Override
    public UpdateAdminResponse update(UUID id, UpdateAdminRequest request) {
        Admin admin = mapper.forRequest().map(request, Admin.class);
        admin.setId(id);
        repository.save(admin);
        UpdateAdminResponse response = mapper.forResponse().map(admin, UpdateAdminResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}