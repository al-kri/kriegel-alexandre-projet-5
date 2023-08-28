package com.openclassrooms.safetynetalerts.service;

import com.openclassrooms.safetynetalerts.entity.Person;
import com.openclassrooms.safetynetalerts.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public void delete(String firstName, String lastName) {
        personRepository.delete(firstName, lastName);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Person> save(Person personAdded) {
        return personRepository.save(personAdded);
    }

    @Override
    public List<Person> update(Person personUpdated) {
        return personRepository.update(personUpdated);
    }
}
