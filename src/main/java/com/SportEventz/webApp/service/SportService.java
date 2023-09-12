package com.SportEventz.webApp.service;


import com.SportEventz.webApp.dto.SportDto;
import com.SportEventz.webApp.models.Sport;

import java.util.List;

public interface SportService {
    List<SportDto> findAllSports();
    Sport saveSport(SportDto sportDto);
    SportDto findSportById(Long sportId);
    void updateSport(SportDto club);
    void delete(Long sportId);
    /*
    List<SportDto> searchSports(String query);

     */
}
