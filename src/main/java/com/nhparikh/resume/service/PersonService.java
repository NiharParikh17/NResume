package com.nhparikh.resume.service;

import com.nhparikh.resume.model.Person;

import java.util.UUID;

public interface PersonService {
    /**
     * Add a person to the db
     * @param person person to be added
     * @return person added to db
     */
    Person addPerson(Person person);

    /**
     * Remove a person by its object
     * @param person person to be removed
     * @return person removed from db
     */
    Person removePerson(Person person);

    /**
     * Remove a person by its id
     * @param uuid id of the person to be removed
     * @return person removed
     */
    Person removePerson(UUID uuid);
}
