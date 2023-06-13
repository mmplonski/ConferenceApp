package com.marcinplonski.conferenceapp.reservations.exception;

public enum ReservationError {
    RESERVATION_NOT_FOUND("Nie znaleziono rezerwacji w bazie danych"),
    RESERVATION_CANCEL_NO_FREE_PLACES("Nie można dokonać rezerwacji z powodu braku miejsc na prelekcje"),
    RESERVATION_ALREADY_MAKE("Nie można dokonać rezerwacji ponieważ użytkownik jest już zapisany na prelekcje o tej godzinie");

    private String message;

    ReservationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
