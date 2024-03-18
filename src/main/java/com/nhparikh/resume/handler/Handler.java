package com.nhparikh.resume.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
public class Handler {
    @ExceptionHandler(NResumeException.class)
    public ResponseEntity<NResumeErrorResponse> handleNResumeException(NResumeException nResumeException) {
        return ResponseEntity
                .status(nResumeException.getError().getCode())
                .body(NResumeErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .errors(Collections.singletonList(NResumeError.builder()
                                        .errorCode(nResumeException.getError().name())
                                        .message(nResumeException.getError().getMessage())
                                .build()))
                        .build());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<NResumeErrorResponse> handleOtherExceptions(Exception exception) {
        return ResponseEntity.internalServerError().body(NResumeErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .errors(Collections.singletonList(NResumeError.builder()
                                        .errorCode(NResumeErrorCodes.NRESUME_ERR_NR_0000.name())
                                        .message(exception.getMessage())
                                .build()))
                .build());
    }
}
