package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.ArtistService;
import com.atmosware.musicapp.business.dto.requests.create.CreateArtistRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateArtistRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateArtistResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllArtistsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetArtistResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateArtistResponse;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.Artist;
import com.atmosware.musicapp.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistManager implements ArtistService {
    private final ArtistRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllArtistsResponse> getAll() {
        List<Artist> artists = repository.findAll();
        List<GetAllArtistsResponse> responses = artists.stream().map(artist -> mapper.forResponse().map(artist, GetAllArtistsResponse.class)).toList();
        return responses;
    }

    @Override
    public GetArtistResponse getById(UUID id) {
        Artist artist = repository.findById(id).orElseThrow();
        GetArtistResponse response = mapper.forResponse().map(artist, GetArtistResponse.class);
        return response;
    }

    @Override
    public CreateArtistResponse add(CreateArtistRequest request) {
        Artist artist = mapper.forRequest().map(request, Artist.class);
        artist.setId(UUID.randomUUID());
        artist.setCreatedAt(LocalDateTime.now());
        Artist createdArtist = repository.save(artist);
        CreateArtistResponse response = mapper.forResponse().map(createdArtist, CreateArtistResponse.class);
        return response;
    }

    @Override
    public UpdateArtistResponse update(UUID id, UpdateArtistRequest request) {
        Artist oldArtist = mapper.forRequest().map(getById(id), Artist.class);
        Artist artist = mapper.forRequest().map(request, Artist.class);
        artist.setId(id);
        artist.setCreatedAt(oldArtist.getCreatedAt());
        artist.setUpdatedAt(LocalDateTime.now());
        repository.save(artist);
        UpdateArtistResponse response = mapper.forResponse().map(artist, UpdateArtistResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}