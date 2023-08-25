package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> findAll();

    List<Person> findByFirstNameAndLastName(String firstName, String lastName);

    List<Person> save(Person personAdded);

    List<Person> update(Person personUpdated);
}
