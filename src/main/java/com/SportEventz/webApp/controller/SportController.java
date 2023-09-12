package com.SportEventz.webApp.controller;


import com.SportEventz.webApp.dto.SportDto;
import com.SportEventz.webApp.models.Sport;
/*
import com.SportEventz.webApp.models.UserEntity;
import com.SportEventz.webApp.security.SecurityUtil;

 */
import com.SportEventz.webApp.service.SportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SportController {
    private SportService sportService;

    @Autowired
    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping("/sports")
    public String listSports(Model model) {
        List<SportDto> sports = sportService.findAllSports();
        model.addAttribute("sports", sports);
        return "sports-list";
    }

    @GetMapping("/sports/{sportId}")
    public String sportDetail(@PathVariable("sportId") long sportId, Model model) {
        SportDto sportDto = sportService.findSportById(sportId);
        model.addAttribute("sport", sportDto);
        return "sports-detail";
    }

    @GetMapping("/sports/new")
    public String createSportForm(Model model) {
        Sport sport = new Sport();
        model.addAttribute("sport", sport);
        return "sports-create";
    }

    @GetMapping("/sports/{sportId}/delete")
    public String deleteSport(@PathVariable("sportId")Long sportId) {
        sportService.delete(sportId);
        return "redirect:/sports";
    }

    /*
    @GetMapping("/sports/search")
    public String searchSport(@RequestParam(value = "query") String query, Model model) {
        List<SportDto> sports = sportService.searchSports(query);
        model.addAttribute("sports", sports);
        return "sports-list";
    }

     */

    @PostMapping("/sports/new")
    public String saveSport(@ModelAttribute("sport") SportDto sportDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("sport", sportDto);
            return "sports-create";
        }
        sportService.saveSport(sportDto);
        return "redirect:/sports";
    }

    @GetMapping("/sports/{sportId}/edit")
    public String editSportForm(@PathVariable("sportId") Long sportId, Model model) {
        SportDto sport = sportService.findSportById(sportId);
        model.addAttribute("sport", sport);
        return "sports-edit";
    }
    @PostMapping("/sports/{sportId}/edit")
    public String updateSport(@PathVariable("sportId") Long sportId,
                             @ModelAttribute("sport") SportDto sport,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("sport", sport);
            return "sports-edit";
        }
        sport.setId(sportId);
        sportService.updateSport(sport);
        return "redirect:/sports";
    }

    @GetMapping("/sports/{sportId}/events")
    public String listSportEvents(@PathVariable Long sportId, Model model) {
        SportDto sport = sportService.findSportById(sportId);
            model.addAttribute("sport", sport);
            model.addAttribute("sportEvents", sport.getSportEvents());
            return "sport-events";
    }
}
