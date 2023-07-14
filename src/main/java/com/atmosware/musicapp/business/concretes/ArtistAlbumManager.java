package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.ArtistAlbumService;
import com.atmosware.musicapp.business.dto.requests.create.CreateArtistAlbumRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateArtistAlbumRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateArtistAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllArtistAlbumsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetArtistAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateArtistAlbumResponse;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.ArtistAlbum;
import com.atmosware.musicapp.repository.ArtistAlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistAlbumManager implements ArtistAlbumService {
    private final ArtistAlbumRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllArtistAlbumsResponse> getAll() {
        List<ArtistAlbum> albums = repository.findAll();
        List<GetAllArtistAlbumsResponse> responses = albums.stream().map(album -> mapper.forResponse().map(album, GetAllArtistAlbumsResponse.class)).toList();
        return responses;
    }

    @Override
    public GetArtistAlbumResponse getById(UUID id) {
        ArtistAlbum album = repository.findById(id).orElseThrow();
        GetArtistAlbumResponse response = mapper.forResponse().map(album, GetArtistAlbumResponse.class);
        return response;
    }

    @Override
    public CreateArtistAlbumResponse add(CreateArtistAlbumRequest request) {
        ArtistAlbum album = mapper.forRequest().map(request, ArtistAlbum.class);
        album.setId(UUID.randomUUID());
        ArtistAlbum createdArtistAlbum = repository.save(album);
        CreateArtistAlbumResponse response = mapper.forResponse().map(createdArtistAlbum, CreateArtistAlbumResponse.class);
        return response;
    }

    @Override
    public UpdateArtistAlbumResponse update(UUID id, UpdateArtistAlbumRequest request) {
        ArtistAlbum album = mapper.forRequest().map(request, ArtistAlbum.class);
        album.setId(id);
        repository.save(album);
        UpdateArtistAlbumResponse response = mapper.forResponse().map(album, UpdateArtistAlbumResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}