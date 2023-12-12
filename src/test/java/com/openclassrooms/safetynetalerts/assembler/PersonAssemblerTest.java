package com.openclassrooms.safetynetalerts.assembler;

import com.openclassrooms.safetynetalerts.dto.PersonDTO;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;
import com.openclassrooms.safetynetalerts.model.PersonInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonAssemblerTest {

    private PersonAssembler personAssembler;

    @BeforeEach
    void beforeEach() {
        personAssembler = new PersonAssembler();
    }

    @Test
    void findByFirstNameAndLastNameModel() {

        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder()
                .firstName("firstNameChild")
                .lastName("lastNameChild")
                .address("123 Main St")
                .email("name.Child@mail.com")
                .build());

        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(MedicalRecord.builder()
                .firstName("firstNameChild")
                .lastName("lastNameChild")
                .birthdate("01/15/2005")
                .allergies(List.of("Peanuts", "Fur"))
                .medications(List.of("Med1", "Med2"))
                .build());

        List<PersonInfo> result = personAssembler.findByFirstNameAndLastNameModel(personList, medicalRecordList);

        assertEquals(1, result.size());

        PersonInfo personInfo1 = result.get(0);
        assertEquals("lastNameChild", personInfo1.getLastName());
        assertEquals("123 Main St", personInfo1.getAddress());
        assertEquals("name.Child@mail.com", personInfo1.getEmail());
        assertEquals(18, personInfo1.getAge());
        assertEquals(List.of("Med1", "Med2"), personInfo1.getMedications());
        assertEquals(List.of("Peanuts", "Fur"), personInfo1.getAllergies());
    }

    @Test
    void toEntity() {

        PersonDTO person1 = (PersonDTO.builder()
                .lastName("Doe")
                .firstName("John")
                .address("123 Main St")
                .city("City")
                .zip("12345")
                .phone("555-1234")
                .email("john.doe@mail.com")
                .build());

        Person result = personAssembler.toEntity(person1);

        assertEquals("Doe", result.getLastName());
        assertEquals("John", result.getFirstName());
        assertEquals("123 Main St", result.getAddress());
        assertEquals("City", result.getCity());
        assertEquals("12345", result.getZip());
        assertEquals("555-1234", result.getPhone());
        assertEquals("john.doe@mail.com", result.getEmail());
    }
}