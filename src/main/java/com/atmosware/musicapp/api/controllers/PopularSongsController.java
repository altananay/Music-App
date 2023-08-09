package com.atmosware.musicapp.api.controllers;


import com.atmosware.musicapp.business.abstracts.PopularSongService;
import com.atmosware.musicapp.business.dto.responses.get.GetAllPopularSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetPopularSongResponse;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/popularSongs")
public class PopularSongsController {

    private final PopularSongService service;

    @GetMapping
    public List<GetAllPopularSongsResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPopularSongResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @GetMapping("/getAllByPagination")
    public List<GetAllPopularSongsResponse> getById(@RequestParam int pageNumber, @RequestParam int pageSize)
    {
        return service.getAllByPagination(pageNumber, pageSize);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id)
    {
        service.delete(id);
    }
    
}