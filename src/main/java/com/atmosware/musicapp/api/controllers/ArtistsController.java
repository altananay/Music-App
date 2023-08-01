package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.ArtistService;
import com.atmosware.musicapp.business.dto.requests.create.CreateArtistRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateArtistRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateArtistResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllArtistsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetArtistResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateArtistResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/artists")
public class ArtistsController {
    private final ArtistService service;

    @GetMapping
    public List<GetAllArtistsResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetArtistResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public CreateArtistResponse add(@RequestBody @Valid CreateArtistRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public UpdateArtistResponse update(@PathVariable UUID id, @RequestBody UpdateArtistRequest request)
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