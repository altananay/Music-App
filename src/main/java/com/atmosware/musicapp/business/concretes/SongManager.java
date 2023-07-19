package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.SongService;
import com.atmosware.musicapp.business.dto.requests.create.CreateSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateSongResponse;
import com.atmosware.musicapp.business.rules.SongBusinessRules;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.Song;
import com.atmosware.musicapp.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SongManager implements SongService {
    private final SongRepository repository;
    private final ModelMapperService mapper;
    private final SongBusinessRules rules;
    @Override
    public List<GetAllSongsResponse> getAll() {
        List<Song> songs = repository.findAll();
        List<GetAllSongsResponse> responses = songs.stream().map(song -> mapper.forResponse().map(song, GetAllSongsResponse.class)).toList();
        return responses;
    }

    @Override
    public GetSongResponse getById(UUID id) {
        rules.CheckIfSongExists(id);
        Song song = repository.findById(id).orElseThrow();
        GetSongResponse response = mapper.forResponse().map(song, GetSongResponse.class);
        return response;
    }

    @Override
    public CreateSongResponse add(CreateSongRequest request) {
        Song song = mapper.forRequest().map(request, Song.class);
        song.setId(UUID.randomUUID());
        song.setCreatedAt(LocalDateTime.now());
        Song createdSong = repository.save(song);
        CreateSongResponse response = mapper.forResponse().map(createdSong, CreateSongResponse.class);
        return response;
    }

    @Override
    public UpdateSongResponse update(UUID id, UpdateSongRequest request) {
        rules.CheckIfSongExists(id);
        Song oldSong = mapper.forRequest().map(getById(id), Song.class);
        Song song = mapper.forRequest().map(request, Song.class);
        song.setId(id);
        song.setCreatedAt(oldSong.getCreatedAt());
        song.setUpdatedAt(LocalDateTime.now());
        repository.save(song);
        UpdateSongResponse response = mapper.forResponse().map(song, UpdateSongResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.CheckIfSongExists(id);
        repository.deleteById(id);
    }
}