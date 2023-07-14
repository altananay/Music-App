package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.ArtistSongService;
import com.atmosware.musicapp.business.dto.requests.create.CreateArtistSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateArtistSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateArtistSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllArtistSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetArtistSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateArtistSongResponse;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.ArtistSong;
import com.atmosware.musicapp.repository.ArtistSongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistSongManager implements ArtistSongService {
    private final ArtistSongRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllArtistSongsResponse> getAll() {
        List<ArtistSong> artistSongs = repository.findAll();
        List<GetAllArtistSongsResponse> responses = artistSongs.stream().map(artistSong -> mapper.forResponse().map(artistSong, GetAllArtistSongsResponse.class)).toList();
        return responses;
    }

    @Override
    public GetArtistSongResponse getById(UUID id) {
        ArtistSong artistSong = repository.findById(id).orElseThrow();
        GetArtistSongResponse response = mapper.forResponse().map(artistSong, GetArtistSongResponse.class);
        return response;
    }

    @Override
    public CreateArtistSongResponse add(CreateArtistSongRequest request) {
        ArtistSong artistSong = mapper.forRequest().map(request, ArtistSong.class);
        artistSong.setId(UUID.randomUUID());
        ArtistSong createdArtistSong = repository.save(artistSong);
        CreateArtistSongResponse response = mapper.forResponse().map(createdArtistSong, CreateArtistSongResponse.class);
        return response;
    }

    @Override
    public UpdateArtistSongResponse update(UUID id, UpdateArtistSongRequest request) {
        ArtistSong artistSong = mapper.forRequest().map(request, ArtistSong.class);
        artistSong.setId(id);
        repository.save(artistSong);
        UpdateArtistSongResponse response = mapper.forResponse().map(artistSong, UpdateArtistSongResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}