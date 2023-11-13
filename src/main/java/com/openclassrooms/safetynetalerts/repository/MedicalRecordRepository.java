package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;

import java.util.List;

public interface MedicalRecordRepository {
    void delete(String firstName, String lastName);

    List<MedicalRecord> findAll();

    List<MedicalRecord> findByFirstNameAndLastName(String firstName, String lastName);
    List<MedicalRecord> findByPersonList(List<Person> personList);

    List<MedicalRecord> save(MedicalRecord medicalRecordAdded);

    List<MedicalRecord> update(MedicalRecord medicalRecordUpdated);
}
