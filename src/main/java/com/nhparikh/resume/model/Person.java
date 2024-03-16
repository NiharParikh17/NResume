package com.nhparikh.resume.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "Id", hidden = true)
    private UUID id;

    @Schema(name = "firstName", title = "First Name", example = "Nihar")
    @Size(min = 1, message = "First name must be at least of size 1")
    private String firstName;

    @Schema(name = "lastName", title = "Last Name", nullable = true, example = "Parikh")
    private String lastName;

    @Schema(name = "middleName", title = "Middle Name", nullable = true, example = "H.")
    private String middleName;

    @Pattern(regexp = "", message = "Not a valid email")
    @Schema(name = "email", title = "Email Address", example = "nihar17999@gmail.com")
    private String email;

    @Pattern(regexp = "", message = "Not a valid phone number")
    @Schema(name = "phone", title = "Phone Number", example = "1234567890")
    private String phone;
}
