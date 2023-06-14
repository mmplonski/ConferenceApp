package com.marcinplonski.conferenceapp.prelections.exception;


public class PrelectionException extends RuntimeException {

    private PrelectionError prelectionError;

    public PrelectionException(PrelectionError prelectionError) {
        this.prelectionError = prelectionError;
    }

    public PrelectionError getPrelectionError() {
        return prelectionError;
    }
}
