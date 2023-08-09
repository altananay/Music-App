package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.UserService;
import com.atmosware.musicapp.business.dto.requests.update.UpdateUserRequest;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetUserResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateUserResponse;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UserService service;

    @GetMapping
    public List<GetAllUsersResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetUserResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UpdateUserResponse update(@PathVariable UUID id, @RequestBody UpdateUserRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}