package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.Firestation;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationRepositoryImpl implements FirestationRepository {

    @Getter
    private final List<Firestation> firestationList = JsonData.firestations;

    @Override
    public void delete(String address, String station) {
        firestationList
                .remove(firestationList.stream()
                        .filter(f -> f.getAddress().equalsIgnoreCase(address) || f.getStation().equalsIgnoreCase(station))
                        .findFirst()
                        .orElse(null));
    }

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
    public List<Firestation> findByStation(String station) {
        return firestationList
                .stream()
                .filter(f -> f.getStation().equalsIgnoreCase(station))
                .toList();
    }

    @Override
    public List<Firestation> save(Firestation firestationAdded) {
        firestationList.add(firestationAdded);
        return firestationList;
    }

    @Override
    public List<Firestation> update(Firestation firestationUpdated) {
        firestationList.stream()
                .filter(f -> f.getAddress().equalsIgnoreCase(firestationUpdated.getAddress()))
                .findFirst()
                .ifPresent(firestationToUpdate -> firestationToUpdate.setStation(firestationUpdated.getStation()));
        return firestationList;
    }
}
