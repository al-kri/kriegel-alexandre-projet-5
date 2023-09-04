package com.openclassrooms.safetynetalerts.assembler;

import com.openclassrooms.safetynetalerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import org.springframework.stereotype.Component;

@Component
public class MedicalRecordAssembler {

    public MedicalRecord toEntity(MedicalRecordDTO medicalRecordDTO) {
        return MedicalRecord.builder()
                .firstName(medicalRecordDTO.getFirstName())
                .lastName(medicalRecordDTO.getLastName())
                .birthdate(medicalRecordDTO.getBirthdate())
                .medications(medicalRecordDTO.getMedications())
                .allergies(medicalRecordDTO.getAllergies())
                .build();
    }
}
