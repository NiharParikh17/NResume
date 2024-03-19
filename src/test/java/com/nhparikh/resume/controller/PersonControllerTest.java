package com.nhparikh.resume.controller;

import com.nhparikh.resume.handler.Handler;
import com.nhparikh.resume.handler.NResumeErrorCodes;
import com.nhparikh.resume.handler.NResumeException;
import com.nhparikh.resume.model.Person;
import com.nhparikh.resume.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private PersonController personController;
    @Mock
    private PersonService personService;
    private static final String URI = "/person";

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(personController)
                .setControllerAdvice(Handler.class)
                .build();
    }

    @Test
    public void addPersonTest_success() throws Exception {
        // Given
        String request = "{\"firstName\": \"Nihar\"," +
                " \"email\": \"nihar17999@gmail.com\"," +
                " \"phone\": \"1234567890\"}";
        Person person = Person.builder().firstName("Nihar")
                .email("nihar17999@gmail.com")
                .phone("1234567890").build();
        // When
        when(personService.addPerson(any(Person.class))).thenReturn(person);
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Nihar"))
                .andExpect(jsonPath("$.middleName").isEmpty())
                .andExpect(jsonPath("$.lastName").isEmpty())
                .andExpect(jsonPath("$.email").value("nihar17999@gmail.com"))
                .andExpect(jsonPath("$.phone").value("1234567890"));
        verify(personService).addPerson(person);
    }

    @Test
    public void addPersonTest_incorrectRequestEmail_failure() throws Exception {
        // Given
        String request = "{\"firstName\": \"Nihar\"," +
                " \"email\": \"notavalidemail\"," +
                " \"phone\": \"1234567890\"}";
        // When
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
        // Then
                .andExpect(status().isBadRequest());
        verifyNoInteractions(personService);
    }

    @Test
    public void addPersonTest_incorrectRequestPhone_failure() throws Exception {
        // Given
        String request = "{\"firstName\": \"Nihar\"," +
                " \"email\": \"nihar17999@gmail.com\"," +
                " \"phone\": \"123890\"}";
        // When
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
        // Then
                .andExpect(status().isBadRequest());
        verifyNoInteractions(personService);
    }

    @Test
    public void addPersonTest_incorrectRequestFirstName_failure() throws Exception {
        // Given
        String request = "{\"email\": \"nihar17999@gmail.com\"," +
                " \"phone\": \"1234567890\"}";
        // When
        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
        // Then
                .andExpect(status().isBadRequest());
        verifyNoInteractions(personService);
    }

    @Test
    public void removePerson_success() throws Exception {
        // Given
        Person person = Person.builder().firstName("Nihar")
                .email("nihar17999@gmail.com")
                .phone("1234567890").build();
        // When
        when(personService.removePerson(any(UUID.class))).thenReturn(person);
        mockMvc.perform(MockMvcRequestBuilders.delete(URI.concat("/").concat(UUID.randomUUID().toString()))
                        .contentType(MediaType.APPLICATION_JSON))
        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Nihar"))
                .andExpect(jsonPath("$.middleName").isEmpty())
                .andExpect(jsonPath("$.lastName").isEmpty())
                .andExpect(jsonPath("$.email").value("nihar17999@gmail.com"))
                .andExpect(jsonPath("$.phone").value("1234567890"));
        verify(personService).removePerson(any(UUID.class));
    }

    @Test
    public void removePerson_noUUID_failure() throws Exception {
        // Given & When
        when(personService.removePerson(any(UUID.class))).thenThrow(new NResumeException(NResumeErrorCodes.NRESUME_ERR_NR_0001));
        mockMvc.perform(MockMvcRequestBuilders.delete(URI.concat("/").concat(UUID.randomUUID().toString()))
                        .contentType(MediaType.APPLICATION_JSON))
        // Then
                .andExpect(status().isNotFound());
        verify(personService).removePerson(any(UUID.class));
    }
}
