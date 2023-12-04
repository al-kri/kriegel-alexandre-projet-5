package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.Firestation;
import com.openclassrooms.safetynetalerts.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonRepositoryImplTest {

    private PersonRepositoryImpl personRepository;

    @BeforeEach
    public void beforeEach() {
        JsonData.loadJsonData();
        personRepository = new PersonRepositoryImpl();
        assertEquals(23, JsonData.persons.size());
    }

    @Test
    void delete() {
        personRepository.delete("John", "Boyd");
        assertEquals(22, personRepository.findAll().size());

    }

    @Test
    void findAll() {
        personRepository.findAll();
        assertEquals(23, personRepository.findAll().size());
    }

    @Test
    void findAllByFirestation() {
        final var stationNumber = List.of(Firestation.builder()
                .address("1509 Culver St")
                .station("3")
                .build());
        personRepository.findAllByFirestation(stationNumber);
        assertEquals(5, personRepository.findAllByFirestation(stationNumber).size());
    }

    @Test
    void findByFirstNameAndLastName() {
        final var person = personRepository.findByFirstNameAndLastName("John", "Boyd");
        assertEquals("John", person.get(0).getFirstName());
        assertEquals("Boyd", person.get(0).getLastName());
    }

    @Test
    void getPersonsEmailByCity() {
        final var nbEmail = personRepository.getPersonsEmailByCity("Culver");
        assertEquals(23, nbEmail.size());
    }

    @Test
    void save() {
        personRepository.save(new Person());
        assertEquals(24, personRepository.findAll().size());
    }

    @Test
    void update() {
        final var personUpdated = Person.builder()
                .firstName("John")
                .lastName("Boyd")
                .address("1509 Culver St")
                .city("Culver")
                .zip("97451")
                .phone("841-874-6512")
                .email("john.boyd@email.com")
                .build();
        List<Person> updatedPersonList = personRepository.update(personUpdated);
        assertEquals("john.boyd@email.com", updatedPersonList.get(0).getEmail());
    }
}