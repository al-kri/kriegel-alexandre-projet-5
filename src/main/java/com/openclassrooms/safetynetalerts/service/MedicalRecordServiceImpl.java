package com.openclassrooms.safetynetalerts.service;

import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<MedicalRecord> findByPersonList(List<Person> personList) {
        return medicalRecordRepository.findByPersonList(personList);
    }

    @Override
    public void delete(String firstName, String lastName) {
        medicalRecordRepository.delete(firstName, lastName);
    }

    @Override
    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public List<MedicalRecord> save(MedicalRecord medicalRecordAdded) {
        return medicalRecordRepository.save(medicalRecordAdded);
    }

    @Override
    public List<MedicalRecord> update(MedicalRecord medicalRecordUpdated) {
        return medicalRecordRepository.update(medicalRecordUpdated);
    }
}
