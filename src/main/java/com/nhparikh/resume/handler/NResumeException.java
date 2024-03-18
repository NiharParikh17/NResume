package com.nhparikh.resume.handler;

import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NResumeException extends RuntimeException{
    private NResumeErrorCodes error;
}
