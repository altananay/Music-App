package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.AlbumSongService;
import com.atmosware.musicapp.business.dto.requests.create.CreateAlbumSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateAlbumSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateAlbumSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAlbumSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllAlbumsSongsResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateAlbumSongResponse;
import com.atmosware.musicapp.business.rules.AlbumSongBusinessRules;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.AlbumSong;
import com.atmosware.musicapp.repository.AlbumSongRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumSongManager implements AlbumSongService {

    private final AlbumSongRepository repository;
    private final AlbumSongBusinessRules rules;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllAlbumsSongsResponse> getAll() {
        List<AlbumSong> albumSongs = repository.findAll();
        List<GetAllAlbumsSongsResponse> responses =
                albumSongs.stream()
                        .map(albumSong -> mapper.forResponse().map(albumSong, GetAllAlbumsSongsResponse.class))
                        .toList();
        return responses;
    }

    @Override
    public List<GetAllAlbumsSongsResponse> getAllByAlbumId(UUID id) {
        List<AlbumSong> albumSongs = repository.getByAlbumId(id);
        List<GetAllAlbumsSongsResponse> responses =
                albumSongs.stream()
                        .map(albumSong -> mapper.forResponse().map(albumSong, GetAllAlbumsSongsResponse.class))
                        .toList();
        return responses;
    }

    @Override
    public GetAlbumSongResponse getById(UUID id) {
        rules.checkIfAlbumSongExists(id);
        AlbumSong albumSong = repository.findById(id).orElseThrow();
        GetAlbumSongResponse response = mapper.forResponse().map(albumSong, GetAlbumSongResponse.class);
        return response;
    }

    @Override
    public CreateAlbumSongResponse add(CreateAlbumSongRequest request) {
        AlbumSong albumSong = mapper.forRequest().map(request, AlbumSong.class);
        albumSong.setId(UUID.randomUUID());
        albumSong.setCreatedAt(LocalDateTime.now());
        AlbumSong createdAlbumSong = repository.save(albumSong);
        CreateAlbumSongResponse response =
                mapper.forResponse().map(createdAlbumSong, CreateAlbumSongResponse.class);
        return response;
    }

    @Override
    public UpdateAlbumSongResponse update(UUID id, UpdateAlbumSongRequest request) {
        rules.checkIfAlbumSongExists(id);
        AlbumSong oldAlbumSong = mapper.forRequest().map(getById(id), AlbumSong.class);
        AlbumSong albumSong = mapper.forRequest().map(request, AlbumSong.class);
        albumSong.setId(id);
        albumSong.setCreatedAt(oldAlbumSong.getCreatedAt());
        albumSong.setUpdatedAt(LocalDateTime.now());
        repository.save(albumSong);
        UpdateAlbumSongResponse response = mapper.forResponse().map(albumSong, UpdateAlbumSongResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfAlbumSongExists(id);
        repository.deleteById(id);
    }
}