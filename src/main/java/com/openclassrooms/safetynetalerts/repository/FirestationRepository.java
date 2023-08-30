package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.Firestation;

import java.util.List;

public interface FirestationRepository {
    List<Firestation> findAll();

    List<Firestation> findByAddress(String address);
}
