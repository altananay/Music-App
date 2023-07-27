package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.PopularSongService;
import com.atmosware.musicapp.business.dto.requests.create.CreatePopularSongRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdatePopularSongRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreatePopularSongResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllPopularSongsResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetPopularSongResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdatePopularSongResponse;
import com.atmosware.musicapp.business.rules.PopularSongBusinessRules;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.PopularSong;
import com.atmosware.musicapp.repository.PopularSongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PopularSongManager implements PopularSongService {

    private final PopularSongRepository repository;
    private final ModelMapperService mapperService;
    private final PopularSongBusinessRules rules;

    @Override
    public CreatePopularSongResponse add(CreatePopularSongRequest request) {
        PopularSong popularSong = mapperService.forRequest().map(request, PopularSong.class);
        popularSong.setId(UUID.randomUUID());
        popularSong.setCreatedAt(LocalDateTime.now());
        repository.save(popularSong);
        CreatePopularSongResponse response = mapperService.forResponse().map(popularSong, CreatePopularSongResponse.class);
        return response;
    }

    @Override
    public GetPopularSongResponse getById(UUID id) {
        rules.checkIfPopularSongExists(id);
        PopularSong popularSong = repository.findById(id).orElseThrow();
        GetPopularSongResponse response = mapperService.forResponse().map(popularSong, GetPopularSongResponse.class);
        return response;
    }

    @Override
    public UpdatePopularSongResponse update(UUID id, UpdatePopularSongRequest request) {
        rules.checkIfPopularSongExists(id);
        PopularSong oldPopularSong = mapperService.forRequest().map(getById(id), PopularSong.class);
        PopularSong popularSong = mapperService.forRequest().map(request, PopularSong.class);
        popularSong.setId(id);
        popularSong.setCreatedAt(oldPopularSong.getCreatedAt());
        popularSong.setUpdatedAt(LocalDateTime.now());
        repository.save(popularSong);
        UpdatePopularSongResponse response = mapperService.forResponse().map(popularSong, UpdatePopularSongResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfPopularSongExists(id);
        repository.deleteById(id);
    }

    @Override
    public List<GetAllPopularSongsResponse> getAll() {
        List<PopularSong> popularSongs = (List<PopularSong>) repository.findAll();
        List<GetAllPopularSongsResponse> responses = popularSongs.stream().map(popularSong -> mapperService.forResponse().map(popularSong, GetAllPopularSongsResponse.class)).toList();
        return responses;
    }
}