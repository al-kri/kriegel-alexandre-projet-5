package com.openclassrooms.safetynetalerts.controller;

import com.openclassrooms.safetynetalerts.assembler.SafetyAlertAssembler;
import com.openclassrooms.safetynetalerts.entity.Firestation;
import com.openclassrooms.safetynetalerts.entity.Person;
import com.openclassrooms.safetynetalerts.model.ChildAlertInfo;
import com.openclassrooms.safetynetalerts.model.FireAlertInfo;
import com.openclassrooms.safetynetalerts.model.FirestationAlertInfo;
import com.openclassrooms.safetynetalerts.model.FloodAlertInfo;
import com.openclassrooms.safetynetalerts.service.FirestationService;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;
import com.openclassrooms.safetynetalerts.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class SafetyAlertController {

    private final PersonService personService;
    private final FirestationService firestationService;
    private final MedicalRecordService medicalRecordService;
    private final SafetyAlertAssembler safetyAlertAssembler;

    @GetMapping("/childAlert")
    public ResponseEntity<List<ChildAlertInfo>> childAlert(@RequestParam(value = "address") final String address) {

        final var listOfPersons = personService.findAll();
        final var listOfMedicalRecords = medicalRecordService.findByPersonList(listOfPersons);
        final var listOfChildrensAndHouseHoldMembers = safetyAlertAssembler.toChildAlertInfoModel(address, listOfPersons, listOfMedicalRecords);

        return new ResponseEntity<>(listOfChildrensAndHouseHoldMembers, HttpStatus.OK);
    }

    @GetMapping("/firestation")
    public ResponseEntity<List<FirestationAlertInfo>> firestationAlert(@RequestParam(value = "station_number") final String stationNumber) {

        final var listOfAddress = firestationService.findByStation(stationNumber);
        final var listOfPersons = personService.findAllByFirestation(listOfAddress);
        final var listOfMedicalRecords = medicalRecordService.findByPersonList(listOfPersons);
        final var listOfPersonsByStation = safetyAlertAssembler.toFirestationAlertInfoModel(listOfPersons, listOfMedicalRecords, listOfAddress);

        return new ResponseEntity<>(listOfPersonsByStation, HttpStatus.OK);
    }

    @GetMapping("/fire")
    public ResponseEntity<List<FireAlertInfo>> fireAlert(@RequestParam(value = "address") final String address) {

        final var firestationList = firestationService.findByAddress(address);
        final var listOfPersons = personService.findAllByFirestation(firestationList);
        final var listOfMedicalRecords = medicalRecordService.findByPersonList(listOfPersons);
        final var listOfPersonsByStations = safetyAlertAssembler.toFireAlertInfoModel(listOfPersons, listOfMedicalRecords, firestationList);

        return new ResponseEntity<>(listOfPersonsByStations, HttpStatus.OK);
    }

    @GetMapping("/flood/stations")
    public ResponseEntity<List<FloodAlertInfo>> floodAlert(@RequestParam(value = "stations") final List<String> firestationList) {

        List<Firestation> listOfAddress = new ArrayList<>();
        for (String stationNumber : firestationList) {
            listOfAddress.addAll(firestationService.findByStation(stationNumber));
        }
        final var listOfPersons = personService.findAllByFirestation(listOfAddress);
        final var listOfMedicalRecords = medicalRecordService.findByPersonList(listOfPersons);
        final var listOfPersonsByStations = safetyAlertAssembler.toFloodAlertInfoModel(listOfPersons, listOfMedicalRecords, listOfAddress);

        return new ResponseEntity<>(listOfPersonsByStations, HttpStatus.OK);
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> phoneAlert(@RequestParam(value = "firestation_number") final String firestationNumber) {

        final var firestationList = firestationService.findByStation(firestationNumber);
        final var personList = personService.findAllByFirestation(firestationList);
        final var listOfPhones = personList.stream()
                .map(Person::getPhone)
                .toList();

        return new ResponseEntity<>(listOfPhones, HttpStatus.OK);
    }


}
