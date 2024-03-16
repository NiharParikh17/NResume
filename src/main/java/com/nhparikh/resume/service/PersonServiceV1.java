package com.nhparikh.resume.service;

import com.nhparikh.resume.model.Person;
import com.nhparikh.resume.respository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceV1 implements PersonService {
    private final PersonRepository personRepository;

    /**
     * Add a person to the db
     *
     * @param person person to be added
     * @return person added to db
     */
    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    /**
     * Remove a person by its object
     *
     * @param person person to be removed
     * @return person removed from db
     */
    @Override
    public Person removePerson(Person person) {
        personRepository.delete(person);
        return person;
    }

    /**
     * Remove a person by its id
     *
     * @param uuid id of the person to be removed
     * @return person removed
     */
    @Override
    public Person removePerson(UUID uuid) {
        Person personToBeRemoved = personRepository.findById(uuid).orElse(null);
        if (personToBeRemoved != null) {
            personRepository.deleteById(uuid);
        } else {
            throw new RuntimeException("No person with that UUID exists");
        }
        return personToBeRemoved;
    }
}
