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
import com.atmosware.musicapp.entities.enums.Role;
import com.atmosware.musicapp.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        admin.setRole(Role.ADMIN);
        admin.setCreatedAt(LocalDateTime.now());
        Admin createdAdmin = repository.save(admin);
        CreateAdminResponse response = mapper.forResponse().map(createdAdmin, CreateAdminResponse.class);
        return response;
    }

    @Override
    public UpdateAdminResponse update(UUID id, UpdateAdminRequest request) {
        Admin oldAdmin = mapper.forRequest().map(getById(id), Admin.class);
        Admin newAdmin = mapper.forRequest().map(request, Admin.class);
        newAdmin.setId(id);
        newAdmin.setUpdatedAt(LocalDateTime.now());
        newAdmin.setCreatedAt(oldAdmin.getCreatedAt());
        newAdmin.setRole(oldAdmin.getRole());
        repository.save(newAdmin);
        UpdateAdminResponse response = mapper.forResponse().map(newAdmin, UpdateAdminResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}