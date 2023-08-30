package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.Firestation;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class FirestationRepositoryImpl implements FirestationRepository {

    private final List<Firestation> firestationList = JsonData.firestations;
    @Override
    public List<Firestation> findAll() {
        return firestationList;
    }

    @Override
    public List<Firestation> findByAddress(String address) {
        return firestationList
                .stream()
                .filter(f -> f.getAddress().equalsIgnoreCase(address))
                .toList();
    }
}
