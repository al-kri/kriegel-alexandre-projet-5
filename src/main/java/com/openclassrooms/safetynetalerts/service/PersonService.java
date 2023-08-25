package com.openclassrooms.safetynetalerts.service;

import com.openclassrooms.safetynetalerts.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
