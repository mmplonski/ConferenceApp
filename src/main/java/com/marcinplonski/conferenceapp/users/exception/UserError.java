package com.marcinplonski.conferenceapp.users.exception;

public enum UserError {
    USER_NOT_FOUND("Nie znaleziono podanego użytkownika w bazie danych"),
    USER_LOGIN_TAKEN("Podany login jest już zajęty");

    private String message;

    UserError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
