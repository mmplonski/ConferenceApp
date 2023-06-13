package com.marcinplonski.conferenceapp.reservations.service;

import com.marcinplonski.conferenceapp.reservations.exception.ReservationException;
import com.marcinplonski.conferenceapp.reservations.model.Reservation;
import com.marcinplonski.conferenceapp.reservations.exception.ReservationError;
import com.marcinplonski.conferenceapp.reservations.repository.ReservationRepository;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationException(ReservationError.RESERVATION_NOT_FOUND));
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.findById(id).orElseThrow(() -> new ReservationException(ReservationError.RESERVATION_NOT_FOUND));
    }

    @Override
    public Reservation patchReservation(Long id, Reservation reservation) {
        return reservationRepository.findById(id).map(reservationFromDB -> {
            if (!ObjectUtils.isEmpty(reservation.getUserId())) {
                reservationFromDB.setUserId(reservation.getUserId());
            }
            if (!ObjectUtils.isEmpty(reservation.getPrelectionId())) {
                reservationFromDB.setPrelectionId(reservation.getPrelectionId());
            }
            return reservationRepository.save(reservationFromDB);
        }).orElseThrow(() -> new ReservationException(ReservationError.RESERVATION_NOT_FOUND));
    }
}
