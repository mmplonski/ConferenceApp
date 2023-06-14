package com.marcinplonski.conferenceapp.conference.service;

import com.marcinplonski.conferenceapp.prelections.model.Path;
import com.marcinplonski.conferenceapp.prelections.model.Prelection;
import com.marcinplonski.conferenceapp.prelections.service.PrelectionService;
import com.marcinplonski.conferenceapp.reservations.service.ReservationService;
import com.marcinplonski.conferenceapp.users.model.User;
import com.marcinplonski.conferenceapp.users.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    private final PrelectionService prelectionService;
    private final UserService userService;
    private final ReservationService reservationService;

    public ConferenceServiceImpl(PrelectionService prelectionService, UserService userService, ReservationService reservationService) {
        this.prelectionService = prelectionService;
        this.userService = userService;
        this.reservationService = reservationService;
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

    @Override
    public String getRegisterUsers() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<pre> ZAREJESTROWANI UŻYTKOWNICY\n");

        userService.getUsers().stream().forEach(user -> {
            stringBuilder.append("\n ID:" + user.getId()  + "\n Login: " + user.getLogin() + "\n Email: " +  user.getEmail() + "\n");
        });
        stringBuilder.append("</pre>");
        return stringBuilder.toString();
    }

    @Override
    public String getStatementsByLectures() {
        StringBuilder stringBuilder = new StringBuilder();
        List<User> users = userService.getUsers();
        stringBuilder.append("<pre> ZESTAWIENIE WYKŁADÓW WG ZAINTERESOWANIA\n");
        prelectionService.getPrelection().stream().forEach(prelection -> {
            stringBuilder.append(prelection.getPath() + ": " + prelection.getDescription() + " Zainteresowanie: " + calculateInterestByLectures(prelection, users) + "\n");
        });

        stringBuilder.append("</pre>");
        return stringBuilder.toString();
    }

    @Override
    public String getStatementsByPaths() {
        StringBuilder stringBuilder = new StringBuilder();
        List<User> users = userService.getUsers();
        stringBuilder.append("<pre> ZESTAWIENIE WYKŁADÓW WG SCIEŻEK\n");
        Path[] paths = Path.values();
        for (Path path : paths) {
            stringBuilder.append(path.getDesc() + " Zainteresowanie: " + calculateInterestByPaths(path));
        }
        stringBuilder.append("</pre>");
        return stringBuilder.toString();
    }

    private String calculateInterestByPaths(Path path) {
        List<Prelection> prelectionsByPath = prelectionService.getPrelectionsByPath(path);
        long numberOfUsersInPath = calculateReservationsByPath(prelectionsByPath);
        Long allUsersWithReservation = reservationService.count();
        if (numberOfUsersInPath != 0) {
            double l = ((double) numberOfUsersInPath / allUsersWithReservation) * 100;
            return Math.round(l) + "%\n";
        } else {
            return "0% \n";
        }
    }

    private long calculateReservationsByPath(List<Prelection> prelectionsByPath) {
        long count = 0l;
        for (Prelection prelection : prelectionsByPath) {
            count += reservationService.countByPrelectionId(prelection.getId());
        }
        return count;
    }

    private String calculateInterestByLectures(Prelection prelection, List<User> users) {
        Long numberOfUsersInPrelection = reservationService.countByPrelectionId(prelection.getId());
        Long allUsersWithReservation = reservationService.count();
        if (numberOfUsersInPrelection != 0) {
            double l = ((double) numberOfUsersInPrelection / allUsersWithReservation) * 100;
            return Math.round(l) + "%";
         } else {
            return "0%";
        }
    }

}
