package com.openclassrooms.safetynetalerts.assembler;

import com.openclassrooms.safetynetalerts.dto.FirestationDTO;
import com.openclassrooms.safetynetalerts.entity.Firestation;
import org.springframework.stereotype.Component;

@Component
public class FirestationAssembler {

    public Firestation toEntity(FirestationDTO firestationDTO) {
        return Firestation.builder()
                .address(firestationDTO.getAddress())
                .station(firestationDTO.getStation())
                .build();
    }
}
