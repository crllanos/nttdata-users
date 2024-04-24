package com.nttdata.users.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class NTTDataExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<NTTDataException> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(String.format("Bad request: %s.", e.getMessage()));
        return ResponseEntity.badRequest().body(NTTDataException.builder()
                .message(e.getMessage())
                .build());
    }
}
