package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.AdminService;
import com.atmosware.musicapp.business.dto.requests.create.CreateAdminRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateAdminRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateAdminResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAdminResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllAdminsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateAdminResponse;
import com.atmosware.musicapp.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/admins")
public class AdminsController {
    private final AdminService service;
    private final AdminRepository repository;

    @GetMapping
    public List<GetAllAdminsResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{username}")
    public String getUsername(String username)
    {
        return repository.findByUsername(username);
    }


    @GetMapping("/{id}")
    public GetAdminResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAdminResponse add(@RequestBody CreateAdminRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateAdminResponse update(@PathVariable UUID id, @RequestBody UpdateAdminRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
