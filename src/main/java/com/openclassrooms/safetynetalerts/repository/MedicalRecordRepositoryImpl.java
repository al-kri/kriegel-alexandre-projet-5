package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    private final List<MedicalRecord> medicalRecordList = JsonData.medicalRecords;

    @Override
    public void delete(String firstName, String lastName) {
        medicalRecordList
                .remove(medicalRecordList.stream()
                        .filter(mr -> mr.getFirstName().equalsIgnoreCase(firstName) && mr.getLastName().equalsIgnoreCase(lastName))
                        .findFirst()
                        .orElse(null));
    }

    @Override
    public List<MedicalRecord> findAll() {
        return medicalRecordList;
    }

    @Override
    public List<MedicalRecord> findByPersonList(List<Person> personList) {

        return personList.stream()
                .map(person ->
                        medicalRecordList
                                .stream()
                                .filter(mr -> person.getFirstName().equalsIgnoreCase(mr.getFirstName()) && person.getLastName().equalsIgnoreCase(mr.getLastName()))
                                .findFirst()
                                .orElse(null)
                )
                .toList();
    }

    @Override
    public List<MedicalRecord> save(MedicalRecord medicalRecordAdded) {
        medicalRecordList.add(medicalRecordAdded);
        return medicalRecordList;
    }

    @Override
    public List<MedicalRecord> update(MedicalRecord medicalRecordUpdated) {
        medicalRecordList.stream()
                .filter(mr -> mr.getFirstName().equalsIgnoreCase(medicalRecordUpdated.getFirstName()) && mr.getLastName().equalsIgnoreCase(medicalRecordUpdated.getLastName()))
                .forEach(medicalRecord -> {
                    medicalRecord.setMedications(medicalRecordUpdated.getMedications());
                    medicalRecord.setAllergies(medicalRecordUpdated.getAllergies());
                });
        return medicalRecordList;
    }
}
