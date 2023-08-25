package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final List<Person> personList = JsonData.persons;

    @Override
    public List<Person> findAll() {
        return personList;
    }

    @Override
    public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        return personList
                .stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
                .toList();
    }

    private Person getPerson(String person) {
        return personList
                .stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(person))
                .findFirst()
                .get();
    }
}

