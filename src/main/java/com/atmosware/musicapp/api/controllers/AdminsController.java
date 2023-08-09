package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.AdminService;
import com.atmosware.musicapp.business.dto.requests.update.UpdateAdminRequest;
import com.atmosware.musicapp.business.dto.responses.get.GetAdminResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllAdminsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateAdminResponse;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admins")
public class AdminsController {
    private final AdminService service;

    @GetMapping
    public List<GetAllAdminsResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetAdminResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
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
