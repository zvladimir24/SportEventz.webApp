package com.SportEventz.webApp.service.imp;

import com.SportEventz.webApp.dto.SportEventDto;
import com.SportEventz.webApp.models.Sport;
import com.SportEventz.webApp.models.SportEvent;
import com.SportEventz.webApp.repository.SportEventRepository;
import com.SportEventz.webApp.repository.SportRepository;
import com.SportEventz.webApp.service.SportEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.SportEventz.webApp.Mapper.SportEventMapper.mapToSportEvent;
import static com.SportEventz.webApp.Mapper.SportEventMapper.mapToSportEventDto;


@Service
public class SportEventServiceImpl implements SportEventService {
    private SportEventRepository sportEventRepository;
    private SportRepository sportRepository;

    @Autowired
    public SportEventServiceImpl(SportEventRepository sportEventRepository, SportRepository sportRepository) {
        this.sportEventRepository = sportEventRepository;
        this.sportRepository = sportRepository;
    }

    @Override
    public SportEvent createSportEvent(Long sportId, SportEventDto sportEventDto) {
        Sport sport = sportRepository.findById(sportId).get();
        SportEvent sportEvent = mapToSportEvent(sportEventDto);
        sportEvent.setSport(sport);
        SportEvent createdSportEvent = sportEventRepository.save(sportEvent);
        return createdSportEvent;
    }

    @Override
    public List<SportEventDto> findAllSportEvents() {
        List<SportEvent> sportEvents = sportEventRepository.findAll();
        return sportEvents.stream().map(sportEvent -> mapToSportEventDto(sportEvent)).collect(Collectors.toList());
    }

    @Override
    public SportEventDto findBySportEventId(Long sportEventId) {
        SportEvent sportEvent = sportEventRepository.findById(sportEventId).get();
        return mapToSportEventDto(sportEvent);
    }

    @Override
    public void updateSportEvent(SportEventDto sportEventDto) {
        SportEvent sportEvent = mapToSportEvent(sportEventDto);
        sportEventRepository.save(sportEvent);
    }

    @Override
    public void deleteSportEvent(long sportEventId) {
        sportEventRepository.deleteById(sportEventId);
    }
}
