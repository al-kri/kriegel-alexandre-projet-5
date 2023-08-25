package com.openclassrooms.safetynetalerts.service;

import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.repository.MedicalRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<MedicalRecord> findByFirstNameAndLastName(String firstName, String lastName) {
        return medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
