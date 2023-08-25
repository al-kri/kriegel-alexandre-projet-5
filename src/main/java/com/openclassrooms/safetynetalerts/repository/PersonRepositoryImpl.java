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

    @Override
    public List<Person> save(Person personAdded) {
        personList.add(personAdded);
        return personList;
    }

    @Override
    public List<Person> update(Person personUpdated) {
        personList.stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(personUpdated.getFirstName()) && p.getLastName().equalsIgnoreCase(personUpdated.getLastName()))
                .forEach(person -> {
                    person.setAddress(personUpdated.getAddress());
                    person.setCity(personUpdated.getCity());
                    person.setZip(personUpdated.getZip());
                    person.setEmail(personUpdated.getEmail());
                    person.setPhone(personUpdated.getPhone());
                });
        return personList;
    }

    private Person getPerson(String person) {
        return personList
                .stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(person))
                .findFirst()
                .get();
    }
}

