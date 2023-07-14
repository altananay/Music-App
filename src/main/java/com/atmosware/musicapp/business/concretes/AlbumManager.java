package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.AlbumService;
import com.atmosware.musicapp.business.dto.requests.create.CreateAlbumRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateAlbumRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllAlbumsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateAlbumResponse;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.Album;
import com.atmosware.musicapp.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AlbumManager implements AlbumService {
    private final AlbumRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllAlbumsResponse> getAll() {
        List<Album> albums = repository.findAll();
        List<GetAllAlbumsResponse> responses = albums.stream().map(album -> mapper.forResponse().map(album, GetAllAlbumsResponse.class)).toList();
        return responses;
    }

    @Override
    public GetAlbumResponse getById(UUID id) {
        Album album = repository.findById(id).orElseThrow();
        GetAlbumResponse response = mapper.forResponse().map(album, GetAlbumResponse.class);
        return response;
    }

    @Override
    public CreateAlbumResponse add(CreateAlbumRequest request) {
        Album album = mapper.forRequest().map(request, Album.class);
        album.setId(UUID.randomUUID());
        Album createdAlbum = repository.save(album);
        CreateAlbumResponse response = mapper.forResponse().map(createdAlbum, CreateAlbumResponse.class);
        return response;
    }

    @Override
    public UpdateAlbumResponse update(UUID id, UpdateAlbumRequest request) {
        Album album = mapper.forRequest().map(request, Album.class);
        album.setId(id);
        repository.save(album);
        UpdateAlbumResponse response = mapper.forResponse().map(album, UpdateAlbumResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}