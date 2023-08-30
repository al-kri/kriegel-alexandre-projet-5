package com.openclassrooms.safetynetalerts.controller;

import com.openclassrooms.safetynetalerts.entity.Firestation;
import com.openclassrooms.safetynetalerts.service.FirestationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FirestationController {

    private final FirestationService firestationService;

    @GetMapping("/firestations")
    public ResponseEntity<List<Firestation>> findAll() {
        return new ResponseEntity<>(firestationService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/firestationInfo", produces = "application/json")
    public ResponseEntity<List<Firestation>> findByAddress(@RequestParam(value = "address") final String address) {
        return new ResponseEntity<>(firestationService.findByAddress(address), HttpStatus.OK);
    }
}
