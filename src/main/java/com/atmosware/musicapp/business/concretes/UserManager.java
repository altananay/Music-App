package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.UserService;
import com.atmosware.musicapp.business.dto.requests.create.CreateUserRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateUserRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateUserResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllUsersResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetUserResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateUserResponse;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.User;
import com.atmosware.musicapp.entities.enums.Role;
import com.atmosware.musicapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllUsersResponse> getAll() {
        List<User> users = repository.findAll();
        List<GetAllUsersResponse> responses = users.stream().map(user -> mapper.forResponse().map(user, GetAllUsersResponse.class)).toList();
        return responses;
    }

    @Override
    public GetUserResponse getById(UUID id) {
        User user = repository.findById(id).orElseThrow();
        GetUserResponse response = mapper.forResponse().map(user, GetUserResponse.class);
        return response;
    }

    @Override
    public CreateUserResponse add(CreateUserRequest request) {
        User user = mapper.forRequest().map(request, User.class);
        user.setId(UUID.randomUUID());
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        User createdUser = repository.save(user);
        CreateUserResponse response = mapper.forResponse().map(createdUser, CreateUserResponse.class);
        return response;
    }

    @Override
    public UpdateUserResponse update(UUID id, UpdateUserRequest request) {
        User oldUser = mapper.forRequest().map(getById(id), User.class);
        User user = mapper.forRequest().map(request, User.class);
        user.setId(id);
        user.setRole(Role.USER);
        user.setCreatedAt(oldUser.getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());
        repository.save(user);
        UpdateUserResponse response = mapper.forResponse().map(user, UpdateUserResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}