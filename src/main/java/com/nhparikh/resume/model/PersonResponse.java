package com.nhparikh.resume.model;

import lombok.*;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonResponse extends Person {
    private UUID id;
}
