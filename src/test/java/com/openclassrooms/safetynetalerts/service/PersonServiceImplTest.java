package com.openclassrooms.safetynetalerts.service;

import com.openclassrooms.safetynetalerts.entity.Person;
import com.openclassrooms.safetynetalerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    void delete() {
        assertDoesNotThrow(() -> personService.delete("delete", "delete"));
        Mockito.verify(personRepository).delete(anyString(), anyString());
    }

    @Test
    void findAll() {
        assertDoesNotThrow(() -> personService.findAll());
        Mockito.verify(personRepository).findAll();
    }

    @Test
    void findAllByFirestation() {
        assertDoesNotThrow(() -> personService.findAllByFirestation(List.of()));
        Mockito.verify(personRepository).findAllByFirestation(anyList());
    }

    @Test
    void findByFirstNameAndLastName() {
        assertDoesNotThrow(() -> personService.findByFirstNameAndLastName("firstName", "lastName"));
        Mockito.verify(personRepository).findByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    void getPersonsEmailByCity() {
        assertDoesNotThrow(() -> personService.getPersonsEmailByCity("city name"));
        Mockito.verify(personRepository).getPersonsEmailByCity(any());
    }

    @Test
    void save() {
        assertDoesNotThrow(() -> personService.save(new Person()));
        Mockito.verify(personRepository).save(any());
    }

    @Test
    void update() {
        assertDoesNotThrow(() -> personService.update(Person.builder().build()));
        Mockito.verify(personRepository).update(any());
    }
}