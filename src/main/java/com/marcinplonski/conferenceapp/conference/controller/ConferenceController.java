package com.marcinplonski.conferenceapp.conference.controller;

import com.marcinplonski.conferenceapp.conference.service.ConferenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conference")
public class ConferenceController {

    private final ConferenceService conferenceService;

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public String getConference() {
        return conferenceService.getConference();
    }

    @GetMapping("/registerUsers")
    public String getRegisterUsers() {
        return conferenceService.getRegisterUsers();
    }
}
