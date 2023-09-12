package com.SportEventz.webApp.Mapper;

import com.SportEventz.webApp.models.SportEvent;
import com.SportEventz.webApp.dto.SportEventDto;


public class SportEventMapper {
    public static SportEvent mapToSportEvent(SportEventDto sportEventDto) {
        return SportEvent.builder()
                .id(sportEventDto.getId())
                .name(sportEventDto.getName())
                .startTime(sportEventDto.getStartTime())
                .endTime(sportEventDto.getEndTime())
                .type(sportEventDto.getType())
                .photoUrl(sportEventDto.getPhotoUrl())
                .createdOn(sportEventDto.getCreatedOn())
                .updatedOn(sportEventDto.getUpdatedOn())
                .sport(sportEventDto.getSport())
                .build();
    }

    public static SportEventDto mapToSportEventDto(SportEvent sportEvent) {
        return SportEventDto.builder()
                .id(sportEvent.getId())
                .name(sportEvent.getName())
                .startTime(sportEvent.getStartTime())
                .endTime(sportEvent.getEndTime())
                .type(sportEvent.getType())
                .photoUrl(sportEvent.getPhotoUrl())
                .createdOn(sportEvent.getCreatedOn())
                .updatedOn(sportEvent.getUpdatedOn())
                .sport(sportEvent.getSport())
                .build();
    }
}
