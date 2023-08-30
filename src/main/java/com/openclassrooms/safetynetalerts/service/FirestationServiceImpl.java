package com.openclassrooms.safetynetalerts.service;

import com.openclassrooms.safetynetalerts.entity.Firestation;
import com.openclassrooms.safetynetalerts.repository.FirestationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FirestationServiceImpl implements FirestationService {

    private final FirestationRepository firestationRepository;

    @Override
    public List<Firestation> findAll() {
        return firestationRepository.findAll();
    }

    @Override
    public List<Firestation> findByAddress(String address) {
        return firestationRepository.findByAddress(address);
    }
}
