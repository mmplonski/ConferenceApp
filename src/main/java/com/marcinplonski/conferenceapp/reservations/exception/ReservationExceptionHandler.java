package com.marcinplonski.conferenceapp.reservations.exception;

import com.marcinplonski.conferenceapp.users.exception.UserError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReservationExceptionHandler {

    @ExceptionHandler(value = ReservationException.class)
    public ResponseEntity<ErrorInfo> handleException(ReservationException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ReservationError.RESERVATION_NOT_FOUND.equals(e.getReservationError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (ReservationError.RESERVATION_ALREADY_MAKE.equals(e.getReservationError()) || ReservationError.RESERVATION_CANCEL_NO_FREE_PLACES.equals(e.getReservationError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getReservationError().getMessage()));
    }
}
