package com.openclassrooms.safetynetalerts.assembler;

import com.openclassrooms.safetynetalerts.dto.PersonDTO;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;
import com.openclassrooms.safetynetalerts.model.PersonInfo;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.openclassrooms.safetynetalerts.utils.Utils.getAgeFromBirthdate;

@Component
public class PersonAssembler {
    public List<PersonInfo> findByFirstNameAndLastNameModel(List<Person> persons, List<MedicalRecord> medicalRecords) {
        return persons.stream()
                .map(person -> {
                    final MedicalRecord medicalRecord = medicalRecords.get(0);
                    return PersonInfo
                            .builder()
                            .lastName(person.getLastName())
                            .address(person.getAddress())
                            .email(person.getEmail())
                            .age(getAgeFromBirthdate(medicalRecord.getBirthdate()))
                            .medications(medicalRecord.getMedications())
                            .allergies(medicalRecord.getAllergies())
                            .build();
                })
                .toList();
    }

    public Person toEntity(PersonDTO personDTO) {
        return Person.builder()
                .lastName(personDTO.getLastName())
                .firstName(personDTO.getFirstName())
                .address((personDTO.getAddress()))
                .city(personDTO.getCity())
                .zip(personDTO.getZip())
                .phone(personDTO.getPhone())
                .email(personDTO.getEmail())
                .build();
    }
}
