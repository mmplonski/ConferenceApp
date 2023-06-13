package com.marcinplonski.conferenceapp.prelections.exception;

public enum PrelectionError {
    PRELECTION_NOT_FOUND("Nie znaleziono podanej prelekcji w bazie danych");

    private String message;

    PrelectionError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
