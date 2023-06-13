package com.marcinplonski.conferenceapp.reservations.exception;

public enum ReservationError {
    RESERVATION_NOT_FOUND("Nie znaleziono rezerwacji w bazie danych");

    private String message;

    ReservationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
