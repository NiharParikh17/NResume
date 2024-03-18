package com.nhparikh.resume.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NResumeErrorResponse {
    private LocalDateTime timestamp;
    private List<NResumeError> errors;
}
