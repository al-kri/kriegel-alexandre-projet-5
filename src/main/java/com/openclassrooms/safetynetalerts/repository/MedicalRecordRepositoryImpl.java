package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    private final List<MedicalRecord> medicalRecordList = JsonData.medicalRecords;

    @Override
    public void delete(String firstName, String lastName) {
        medicalRecordList
                .stream()
                        .filter(mr -> mr.getFirstName().equalsIgnoreCase(firstName) && mr.getLastName().equalsIgnoreCase(lastName))
                        .findFirst()
                        .ifPresentOrElse(medicalRecord -> {
                            medicalRecordList.remove(medicalRecord);
                            log.info("MedicalRecord of " + firstName + " " + lastName + " successfully deleted");
                        }, () -> log.info("MedicalRecord is not present in database and can't be deleted"));
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
        AtomicBoolean recordFound = new AtomicBoolean(false);

        medicalRecordList.stream()
                .filter(mr -> mr.getFirstName().equalsIgnoreCase(medicalRecordUpdated.getFirstName()) && mr.getLastName().equalsIgnoreCase(medicalRecordUpdated.getLastName()))
                .forEach(medicalRecord -> {
                    medicalRecord.setMedications(medicalRecordUpdated.getMedications());
                    medicalRecord.setAllergies(medicalRecordUpdated.getAllergies());
                    recordFound.set(true);
                });

        if(recordFound.get()) {
            log.info("MedicalRecord of : " + medicalRecordUpdated.getFirstName() + " " + medicalRecordUpdated.getLastName() + " successfully updated");
        } else {
            log.info("MedicalRecord not found in database and can't be updated");
        }
        return medicalRecordList;
    }
}
