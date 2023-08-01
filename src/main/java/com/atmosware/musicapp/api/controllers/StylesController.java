package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.abstracts.StyleService;
import com.atmosware.musicapp.business.dto.requests.create.CreateStyleRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateStyleRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateStyleResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllStylesResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetStyleResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateStyleResponse;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/styles")
public class StylesController {

    private final StyleService service;

    @GetMapping
    public List<GetAllStylesResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetStyleResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateStyleResponse add(@RequestBody @Valid CreateStyleRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateStyleResponse update(@PathVariable UUID id, @RequestBody UpdateStyleRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
