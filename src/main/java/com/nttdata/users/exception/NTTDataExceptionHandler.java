package com.nttdata.users.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Slf4j
@ControllerAdvice
public class NTTDataExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<NTTDataException> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(String.format("BAD REQUEST: %s.", e.getMessage()));
        return ResponseEntity.badRequest().body(NTTDataException.builder()
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<NTTDataException> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(String.format("NOT FOUND: %s.", e.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(NTTDataException.builder()
                        .message(e.getMessage())
                        .build());
    }
}
