package com.marcinplonski.conferenceapp.prelections.exception;

import com.marcinplonski.conferenceapp.users.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PrelectionExceptionHandler {

    @ExceptionHandler(value = PrelectionException.class)
    public ResponseEntity<ErrorInfo> handleException(PrelectionException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (PrelectionError.PRELECTION_NOT_FOUND.equals(e.getPrelectionError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getPrelectionError().getMessage()));
    }
}
