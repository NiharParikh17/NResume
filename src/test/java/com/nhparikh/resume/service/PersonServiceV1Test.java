package com.nhparikh.resume.service;

import com.nhparikh.resume.model.Person;
import com.nhparikh.resume.respository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceV1Test {
    @InjectMocks
    private PersonServiceV1 personServiceV1;
    @Mock
    private PersonRepository personRepository;

    @Test
    public void addPersonTest_success() {
        // Given
        Person expectedPerson = Person.builder().build();
        // When
        when(personRepository.save(expectedPerson)).thenReturn(expectedPerson);
        Person actualPerson = personServiceV1.addPerson(expectedPerson);
        // Then
        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void removePersonTest_withPersonObj_success() {
        // Given
        Person expectedPerson = Person.builder().build();
        // When
        doNothing().when(personRepository).delete(expectedPerson);
        Person actualPerson = personServiceV1.removePerson(expectedPerson);
        // Then
        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void removePersonTest_withUUID_success() {
        // Given
        Person expectedPerson = Person.builder().id(UUID.randomUUID()).build();
        // When
        when(personRepository.findById(expectedPerson.getId())).thenReturn(Optional.of(expectedPerson));
        doNothing().when(personRepository).deleteById(expectedPerson.getId());
        Person actualPerson = personServiceV1.removePerson(expectedPerson.getId());
        // Then
        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void removePersonTest_withUUID_NoUserWithThatUUID_failure() {
        // Given
        Person expectedPerson = Person.builder().id(UUID.randomUUID()).build();
        // When
        when(personRepository.findById(expectedPerson.getId())).thenReturn(Optional.empty());
        // Then
        assertThrows(RuntimeException.class, () -> personServiceV1.removePerson(expectedPerson.getId()));
        verify(personRepository, VerificationModeFactory.times(0)).deleteById(any(UUID.class));
    }
}
