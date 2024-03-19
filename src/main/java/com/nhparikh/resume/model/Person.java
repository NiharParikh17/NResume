package com.nhparikh.resume.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.nhparikh.resume.factory.RegexFactory.EMAIL_REGEX;
import static com.nhparikh.resume.factory.RegexFactory.PHONE_REGEX;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "id", title = "Identifier", accessMode = Schema.AccessMode.READ_ONLY, example = "0f2d25b0-48b2-4eec-8c96-a09e621782e7")
    private UUID id;

    @NotNull(message = "First name cannot be null")
    @Size(min = 1, message = "First name must be at least of size 1")
    @Schema(name = "firstName", title = "First Name", example = "Nihar")
    private String firstName;

    @Schema(name = "lastName", title = "Last Name", nullable = true, example = "Parikh")
    private String lastName;

    @Schema(name = "middleName", title = "Middle Name", nullable = true, example = "H.")
    private String middleName;

    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = EMAIL_REGEX, message = "Not a valid email")
    @Schema(name = "email", title = "Email Address", example = "nihar17999@gmail.com")
    private String email;

    @NotNull(message = "Phone cannot be null")
    @Size(min = 10, message = "Phone number must be of size 10")
    @Pattern(regexp = PHONE_REGEX, message = "Not a valid phone number")
    @Schema(name = "phone", title = "Phone Number", example = "1234567890")
    private String phone;
}
