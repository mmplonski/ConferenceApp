package com.marcinplonski.conferenceapp.reservations.exception;

public class ReservationException extends RuntimeException {
    private ReservationError reservationError;

    public ReservationException(ReservationError reservationError) {
        this.reservationError = reservationError;
    }

    public ReservationError getReservationError() {
        return reservationError;
    }
}
