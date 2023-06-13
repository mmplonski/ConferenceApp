package com.marcinplonski.conferenceapp.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ErrorInfo> handleException(UserException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (UserError.USER_NOT_FOUND.equals(e.getUserError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (UserError.USER_LOGIN_TAKEN.equals(e.getUserError()) || UserError.USER_EMAIL_TAKEN.equals(e.getUserError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getUserError().getMessage()));
    }
}
