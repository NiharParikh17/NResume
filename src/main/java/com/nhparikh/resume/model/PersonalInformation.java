package com.nhparikh.resume.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInformation {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
}
