package com.marcinplonski.conferenceapp.reservations.service;


import com.marcinplonski.conferenceapp.reservations.model.Reservation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ReservationService {
    List<Reservation> getReservations();

    Reservation getReservation(Long id);

    Reservation addReservation(String login, String email, Long prelectionId);

    void deleteReservation(Long id);

    Reservation patchReservation(Long id, Reservation reservation);
}
