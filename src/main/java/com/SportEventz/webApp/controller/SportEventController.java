package com.SportEventz.webApp.controller;
import com.SportEventz.webApp.dto.SportDto;
import com.SportEventz.webApp.dto.SportEventDto;
import com.SportEventz.webApp.models.SportEvent;
/*
import com.SportEventz.webApp.models.UserEntity;
import com.SportEventz.webApp.security.SecurityUtil;

 */
import com.SportEventz.webApp.service.SportService;
import com.SportEventz.webApp.service.SportEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SportEventController {

    private SportEventService sportEventService;
    private SportService sportService;

    @Autowired
    public SportEventController(SportEventService sportEventService) {
        this.sportEventService = sportEventService;
    }

    @GetMapping("/sportEvents")
    public String sportEventList(Model model) {
        List<SportEventDto> sportEvents = sportEventService.findAllSportEvents();
        model.addAttribute("sportEvents", sportEvents);
        return "sportEvents-list";
    }

    @GetMapping("/sportEvents/{sportEventId}")
    public String viewSportEvent(@PathVariable("sportEventId")Long sportEventId, Model model) {
        SportEventDto sportEventDto = sportEventService.findBySportEventId(sportEventId);
        model.addAttribute("sport", sportEventDto.getSport());
        model.addAttribute("sportEvent", sportEventDto);
        return "sportEvents-detail";
    }

    @GetMapping("/sportEvents/{sportId}/new")
    public String createSportEventForm(@PathVariable("sportId") Long sportId, Model model) {
        SportEvent sportEvent = new SportEvent();
        model.addAttribute("sportId", sportId);
        model.addAttribute("sportEvent", sportEvent);
        return "sportEvents-create";
    }

    @GetMapping("/sportEvents/{sportEventId}/edit")
    public String editSportEventForm(@PathVariable("sportEventId") Long sportEventId, Model model) {
        SportEventDto sportEvent = sportEventService.findBySportEventId(sportEventId);
        model.addAttribute("sportEvent", sportEvent);
        return "sportEvents-edit";
    }

    @PostMapping("/sportEvents/{sportId}")
    public String createSportEvent(@PathVariable("sportId") Long sportId, @ModelAttribute("sportEvent") SportEventDto sportEventDto,
                              BindingResult result,
                              Model model) {
        if(result.hasErrors()) {
            model.addAttribute("sportEvent", sportEventDto);
            return "sports-create";
        }
        SportEvent createdSportEvent = sportEventService.createSportEvent(sportId, sportEventDto);
        return "redirect:/sportEvents/" + createdSportEvent.getId();
    }

    @PostMapping("/sportEvents/{sportEventId}/edit")
    public String updateSportEvent(@PathVariable("sportEventId") Long sportEventId, @ModelAttribute("sportEvent") SportEventDto sportEvent,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("sportEvent", sportEvent);
            return "sportEvents-edit";
        }
        SportEventDto sportEventDto = sportEventService.findBySportEventId(sportEventId);
        sportEvent.setId(sportEventId);
        sportEvent.setSport(sportEventDto.getSport());
        sportEventService.updateSportEvent(sportEvent);
        return "redirect:/sportEvents";
    }

    @GetMapping("/sportEvents/{sportEventId}/delete")
    public String deleteSportEvent(@PathVariable("sportEventId") long sportEventId) {
        sportEventService.deleteSportEvent(sportEventId);
        return "redirect:/sportEvents";
    }

}
