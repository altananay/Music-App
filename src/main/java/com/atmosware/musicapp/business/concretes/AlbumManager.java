package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.AlbumService;
import com.atmosware.musicapp.business.dto.requests.create.CreateAlbumRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateAlbumRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAlbumResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllAlbumsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateAlbumResponse;
import com.atmosware.musicapp.business.rules.AlbumBusinessRules;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.Album;
import com.atmosware.musicapp.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
@Slf4j
public class AlbumManager implements AlbumService {
    private final AlbumRepository repository;
    private final ModelMapperService mapper;
    private final AlbumBusinessRules rules;

    @Override
    public List<GetAllAlbumsResponse> getAll() {
        List<Album> albums = repository.findAll();
        List<GetAllAlbumsResponse> responses = albums.stream().map(album -> mapper.forResponse().map(album, GetAllAlbumsResponse.class)).toList();
        log.info("albumler listelendi");
        return responses;
    }

    @Override
    public GetAlbumResponse getById(UUID id) {
        rules.CheckIfAlbumExists(id);
        Album album = repository.findById(id).orElseThrow();
        GetAlbumResponse response = mapper.forResponse().map(album, GetAlbumResponse.class);
        return response;
    }

    @Override
    public CreateAlbumResponse add(CreateAlbumRequest request) {
        Album album = mapper.forRequest().map(request, Album.class);
        album.setId(UUID.randomUUID());
        album.setCreatedAt(LocalDateTime.now());
        Album createdAlbum = repository.save(album);
        CreateAlbumResponse response = mapper.forResponse().map(createdAlbum, CreateAlbumResponse.class);
        return response;
    }

    @Override
    public UpdateAlbumResponse update(UUID id, UpdateAlbumRequest request) {
        rules.CheckIfAlbumExists(id);
        Album oldAlbum = mapper.forRequest().map(getById(id), Album.class);
        Album album = mapper.forRequest().map(request, Album.class);
        album.setId(id);
        album.setCreatedAt(oldAlbum.getCreatedAt());
        album.setUpdatedAt(LocalDateTime.now());
        repository.save(album);
        UpdateAlbumResponse response = mapper.forResponse().map(album, UpdateAlbumResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.CheckIfAlbumExists(id);
        repository.deleteById(id);
    }
}