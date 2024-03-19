package com.nhparikh.resume.handler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@Schema(name = "NResumeErrorCodes", description = "Error codes for error responses and their description")
public enum NResumeErrorCodes {
    // Person Handler
    NRESUME_ERR_NR_0001(HttpStatusCode.valueOf(404), "No Person with that UUID exists"),
    // Others
    NRESUME_ERR_NR_0400(HttpStatusCode.valueOf(400), "One of the field values is not correct"),
    NRESUME_ERR_NR_0000(HttpStatusCode.valueOf(500), "Internal Server Error");

    private final HttpStatusCode code;
    private final String message;
}
