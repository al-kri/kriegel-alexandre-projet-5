package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.Firestation;
import com.openclassrooms.safetynetalerts.entity.Person;

import java.util.List;

public interface PersonRepository {

    void delete(String firstName, String lastName);

    List<Person> findAll();

    List<Person> findByFirstNameAndLastName(String firstName, String lastName);

    List<Person> save(Person personAdded);

    List<Person> update(Person personUpdated);

    List<String> getPersonsEmailByCity(String city);

    List<Person> findAllByFirestation(List<Firestation> firestationList);



    }
