package com.marcinplonski.conferenceapp.reservations.service;


import com.marcinplonski.conferenceapp.reservations.model.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getReservations();

    Reservation getReservation(Long id);

    Reservation addReservation(Reservation reservation);

    void deleteReservation(Long id);

    Reservation patchReservation(Long id, Reservation reservation);
}
