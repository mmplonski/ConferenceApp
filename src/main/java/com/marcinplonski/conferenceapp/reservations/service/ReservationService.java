package com.marcinplonski.conferenceapp.reservations.service;

import com.marcinplonski.conferenceapp.reservations.model.Reservation;
import java.util.List;

public interface ReservationService {
    List<Reservation> getReservations();

    Reservation getReservation(Long id);

    Reservation addReservation(String login, String email, Long prelectionId);

    void deleteReservation(Long id);

    Reservation patchReservation(Long id, Reservation reservation);

    Long countByPrelectionId(Long prelectionId);

    Long count();

    List<Reservation> getReservationsByUserId(Long userId);

    String getUserReservations(Long userId);
}
