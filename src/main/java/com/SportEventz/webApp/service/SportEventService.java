package com.SportEventz.webApp.service;

import com.SportEventz.webApp.dto.SportEventDto;
import com.SportEventz.webApp.models.SportEvent;

import java.util.List;

public interface SportEventService {
    SportEvent createSportEvent(Long sportId, SportEventDto sportEventDto);
    List<SportEventDto> findAllSportEvents();
    SportEventDto findBySportEventId(Long sportEventId);
    void updateSportEvent(SportEventDto sportEventDto);
    void deleteSportEvent(long sportEventId);
}
