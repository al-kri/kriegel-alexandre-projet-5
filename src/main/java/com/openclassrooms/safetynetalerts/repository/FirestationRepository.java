package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.Firestation;

import java.util.List;

public interface FirestationRepository {
    void delete(String address, String station);

    List<Firestation> findAll();

    List<Firestation> findByAddress(String address);

    List<Firestation> save(Firestation firestationAdded);

    List<Firestation> update(Firestation firestationUpdated);
}
