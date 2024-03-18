package com.nhparikh.resume.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class Handler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<NResumeErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.badRequest().body(NResumeErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .errors(methodArgumentNotValidException.getFieldErrors().stream()
                                .map(fieldError -> NResumeError.builder()
                                        .errorCode(NResumeErrorCodes.NRESUME_ERR_NR_0400.name())
                                        .message(fieldError.getField().concat(" - ").concat(Objects.requireNonNull(fieldError.getDefaultMessage())))
                                        .build()).collect(Collectors.toList()))
                .build());
    }

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
