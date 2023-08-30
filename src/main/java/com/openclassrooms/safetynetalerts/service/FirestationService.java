package com.openclassrooms.safetynetalerts.service;

import com.openclassrooms.safetynetalerts.entity.Firestation;

import java.util.List;

public interface FirestationService {
    List<Firestation> findAll();

    List<Firestation> findByAddress(String address);
}
