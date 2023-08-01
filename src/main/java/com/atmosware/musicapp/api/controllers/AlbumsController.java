package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.AlbumService;
import com.atmosware.musicapp.business.dto.requests.create.CreateAlbumRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateAlbumRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllAlbumsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateAlbumResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/albums")
public class AlbumsController {
    private final AlbumService service;

    @GetMapping
    public List<GetAllAlbumsResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetAlbumResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public CreateAlbumResponse add(@RequestBody @Valid CreateAlbumRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public UpdateAlbumResponse update(@PathVariable UUID id, @RequestBody UpdateAlbumRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}