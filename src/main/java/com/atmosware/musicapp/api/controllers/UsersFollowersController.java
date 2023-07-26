package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.UserFollowerService;
import com.atmosware.musicapp.business.dto.requests.create.CreateUserFollowerRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateUserFollowerRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateUserFollowerResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersFollowersResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetUserFollowerResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateUserFollowerResponse;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/usersFollowers")
public class UsersFollowersController {
    private final UserFollowerService service;

    @GetMapping
    public List<GetAllUsersFollowersResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetUserFollowerResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @GetMapping("/getByUserId/{id}")
    public List<GetAllUsersFollowersResponse> getByUserId(@PathVariable UUID id)
    {
        return service.getByUserId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserFollowerResponse add(@RequestBody CreateUserFollowerRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateUserFollowerResponse update(@PathVariable UUID id, @RequestBody UpdateUserFollowerRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}