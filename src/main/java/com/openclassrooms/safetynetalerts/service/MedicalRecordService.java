package com.openclassrooms.safetynetalerts.service;

import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;

import java.util.List;

public interface MedicalRecordService {

    List<MedicalRecord> findByPersonList(List<Person> personList);

    void delete(String firstName, String lastName);

    List<MedicalRecord> findAll();

    List<MedicalRecord> save(MedicalRecord medicalRecordAdded);

    List<MedicalRecord> update(MedicalRecord medicalRecordUpdated);
}
