package com.nhparikh.resume.controller;

import com.nhparikh.resume.model.Person;
import com.nhparikh.resume.service.PersonService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/person")
public class PersonController {
    private final PersonService personService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Person.class)))
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> addPerson(@RequestBody @Valid Person person) {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Person.class)))
    })
    @DeleteMapping(path = "/{uuid}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> removePerson(@PathVariable(value = "uuid") @Schema(requiredMode = Schema.RequiredMode.REQUIRED) UUID uuid) {
        return ResponseEntity.ok(personService.removePerson(uuid));
    }
}
