package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.ArtistAlbumService;
import com.atmosware.musicapp.business.dto.requests.create.CreateArtistAlbumRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateArtistAlbumRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateArtistAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetArtistAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllArtistAlbumsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateArtistAlbumResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/artistAlbums")
public class ArtistAlbumsController {
    private final ArtistAlbumService service;

    @GetMapping
    public List<GetAllArtistAlbumsResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetArtistAlbumResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateArtistAlbumResponse add(@RequestBody CreateArtistAlbumRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateArtistAlbumResponse update(@PathVariable UUID id, @RequestBody UpdateArtistAlbumRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}