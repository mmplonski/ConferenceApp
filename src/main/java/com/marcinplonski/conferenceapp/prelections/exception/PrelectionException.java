package com.marcinplonski.conferenceapp.prelections.exception;

import com.marcinplonski.conferenceapp.users.exception.UserError;

public class PrelectionException extends RuntimeException {

    private PrelectionError prelectionError;

    public PrelectionException(PrelectionError prelectionError) {
        this.prelectionError = prelectionError;
    }

    public PrelectionError getPrelectionError() {
        return prelectionError;
    }
}
