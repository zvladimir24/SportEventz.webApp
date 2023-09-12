package com.SportEventz.webApp.service.imp;

import com.SportEventz.webApp.dto.SportDto;
import com.SportEventz.webApp.models.Sport;
import com.SportEventz.webApp.repository.SportRepository;
import com.SportEventz.webApp.models.UserEntity;
import com.SportEventz.webApp.repository.SportRepository;
import com.SportEventz.webApp.repository.UserRepository;
import com.SportEventz.webApp.security.SecurityUtil;

import com.SportEventz.webApp.security.SecurityUtil;
import com.SportEventz.webApp.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.SportEventz.webApp.Mapper.SportMapper.mapToSport;
import static com.SportEventz.webApp.Mapper.SportMapper.mapToSportDto;


@Service
public class SportServiceImpl implements SportService {
    private SportRepository sportRepository;

    private UserRepository userRepository;

    @Autowired
    public SportServiceImpl(SportRepository sportRepository, UserRepository userRepository) {
        this.sportRepository = sportRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<SportDto> findAllSports() {
        List<Sport> sports = sportRepository.findAll();
        return sports.stream().map((sport) -> mapToSportDto(sport)).collect(Collectors.toList());
    }

    @Override
    public Sport saveSport(SportDto sportDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);

        if (user == null){
            throw new IllegalStateException("User not found or logged in.");
        }
        Sport sport = mapToSport(sportDto);
        sport.setCreatedBy(user);
        return sportRepository.save(sport);
    }

    @Override
    public SportDto findSportById(Long sportId) {
        Sport sport = sportRepository.findById(sportId).get();
        return mapToSportDto(sport);
    }

    @Override
    public void updateSport(SportDto sportDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Sport sport = mapToSport(sportDto);
        sport.setCreatedBy(user);
        sportRepository.save(sport);
    }

    @Override
    public void delete(Long sportId) {
        sportRepository.deleteById(sportId);
    }

    /*
    @Override
    public List<SportDto> searchSports(String query) {
        List<Sport> sports = sportRepository.searchSports(query);
        return sports.stream().map(sport-> mapToSportDto(sport)).collect(Collectors.toList());
    }

     */
}
