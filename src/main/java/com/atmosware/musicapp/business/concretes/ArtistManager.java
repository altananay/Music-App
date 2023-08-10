package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.ArtistService;
import com.atmosware.musicapp.business.dto.requests.create.CreateArtistRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateArtistRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateArtistResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllArtistsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetArtistResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateArtistResponse;
import com.atmosware.musicapp.business.rules.ArtistBusinessRules;
import com.atmosware.musicapp.common.utils.annotations.Logger;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.Artist;
import com.atmosware.musicapp.repository.ArtistRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArtistManager implements ArtistService {
    private final ArtistRepository repository;
    private final ModelMapperService mapper;
    private final ArtistBusinessRules rules;
    @Override
    @Logger
    public List<GetAllArtistsResponse> getAll() {
        List<Artist> artists = repository.findAll();
        List<GetAllArtistsResponse> responses = artists.stream().map(artist -> mapper.forResponse().map(artist, GetAllArtistsResponse.class)).toList();
        return responses;
    }

    @Override
    @Logger
    public GetArtistResponse getById(UUID id) {
        rules.checkIfArtistExists(id);
        Artist artist = repository.findById(id).orElseThrow();
        GetArtistResponse response = mapper.forResponse().map(artist, GetArtistResponse.class);
        return response;
    }

    @Override
    @Logger
    public CreateArtistResponse add(CreateArtistRequest request) {
        rules.checkIfArtistExistsByName(request.getName());
        Artist artist = mapper.forRequest().map(request, Artist.class);
        artist.setId(UUID.randomUUID());
        artist.setCreatedAt(LocalDateTime.now());
        Artist createdArtist = repository.save(artist);
        CreateArtistResponse response = mapper.forResponse().map(createdArtist, CreateArtistResponse.class);
        return response;
    }

    @Override
    @Logger
    public UpdateArtistResponse update(UUID id, UpdateArtistRequest request) {
        rules.checkIfArtistExists(id);
        rules.checkIfArtistExistsByName(request.getName());
        Artist oldArtist = mapper.forRequest().map(getById(id), Artist.class);
        Artist artist = mapper.forRequest().map(request, Artist.class);
        artist.setId(id);
        artist.setCreatedAt(oldArtist.getCreatedAt());
        artist.setUpdatedAt(LocalDateTime.now());
        artist.setName(request.getName());
        repository.save(artist);
        UpdateArtistResponse response = mapper.forResponse().map(artist, UpdateArtistResponse.class);
        return response;
    }

    @Override
    @Logger
    public void delete(UUID id) {
        rules.checkIfArtistExists(id);
        repository.deleteById(id);
    }
}