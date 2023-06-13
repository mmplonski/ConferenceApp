package com.marcinplonski.conferenceapp.conference.service;

import com.marcinplonski.conferenceapp.prelections.service.PrelectionService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    private final PrelectionService prelectionService;

    public ConferenceServiceImpl(PrelectionService prelectionService) {
        this.prelectionService = prelectionService;
    }

    @Override
    public String getConference() {
        StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<pre>Czas trwania prelekcji: 10:00 - 11:45\n" +
                        "Przerwa na kawę: 11:45 - 12:00\n");

        prelectionService.getPrelectionsByStartTime(LocalTime.of(10, 00)).stream().forEach(prelection -> {
            stringBuilder.append(prelection.getPath().toString() + ": " + prelection.getDescription() + "(" + prelection.getAvailableSeats() + "/5 wolnych miejsc)\n");
        });
        stringBuilder.append("<pre>Czas trwania prelekcji: 12:00 - 13:45\n" +
                "Przerwa na kawę: 11:45 - 12:00\n");
        prelectionService.getPrelectionsByStartTime(LocalTime.of(12, 00)).stream().forEach(prelection -> {
            stringBuilder.append(prelection.getPath().toString() + ": " + prelection.getDescription() + "(" + prelection.getAvailableSeats() + "/5 wolnych miejsc)\n");
        });

        stringBuilder.append("<pre>Czas trwania prelekcji: 14:00 - 15:45\n" +
                "Przerwa na kawę: 11:45 - 12:00\n");

        prelectionService.getPrelectionsByStartTime(LocalTime.of(14, 00)).stream().forEach(prelection -> {
            stringBuilder.append(prelection.getPath().toString() + ": " + prelection.getDescription() + "(" + prelection.getAvailableSeats() + "/5 wolnych miejsc)\n");
        });
        stringBuilder.append("</pre>");

        return stringBuilder.toString();
    }
}
