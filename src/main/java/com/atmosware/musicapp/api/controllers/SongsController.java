package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.SongService;
import com.atmosware.musicapp.business.dto.requests.create.CreateSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllSongsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateSongResponse;
import com.atmosware.musicapp.core.utils.interfaces.MusicDto;
import com.atmosware.musicapp.repository.SongRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/songs")
public class SongsController {
    private final SongService service;
    private final SongRepository repository;

    @GetMapping
    public List<GetAllSongsResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/getMusicsWithDetails")
    public List<MusicDto> getAllWithDetails()
    {
        return repository.getMusicWithDetails();
    }

    @GetMapping("/{id}")
    public GetSongResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public CreateSongResponse add(@RequestBody @Valid CreateSongRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public UpdateSongResponse update(@PathVariable UUID id, @RequestBody UpdateSongRequest request)
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