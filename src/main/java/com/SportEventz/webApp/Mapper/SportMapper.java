package com.SportEventz.webApp.Mapper;

import com.SportEventz.webApp.dto.SportDto;
import com.SportEventz.webApp.models.Sport;
import static com.SportEventz.webApp.Mapper.SportEventMapper.mapToSportEventDto;


import java.util.stream.Collectors;


public class SportMapper {
    public static Sport mapToSport(SportDto sport) {
        Sport sportDto = Sport.builder()
                .id(sport.getId())
                .createdBy(sport.getCreatedBy())
                .title(sport.getTitle())
                .photoUrl(sport.getPhotoUrl())
                .content(sport.getContent())
                .createdOn(sport.getCreatedOn())
                .updatedOn(sport.getUpdatedOn())
                .build();
        return  sportDto;
    }

    public static SportDto mapToSportDto(Sport sport) {
        SportDto sportDto = SportDto.builder()
                .id(sport.getId())
                .title(sport.getTitle())
                .photoUrl(sport.getPhotoUrl())
                .createdBy(sport.getCreatedBy())
                .content(sport.getContent())
                .createdOn(sport.getCreatedOn())
                .updatedOn(sport.getUpdatedOn())
                .sportEvents(sport.getSportEvents().stream().map((sportEvent) -> mapToSportEventDto(sportEvent)).collect(Collectors.toList()))
                .build();
        return sportDto;
    }
}
