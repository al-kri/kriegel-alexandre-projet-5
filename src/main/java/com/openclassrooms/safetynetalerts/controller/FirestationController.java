package com.openclassrooms.safetynetalerts.controller;

import com.openclassrooms.safetynetalerts.assembler.FirestationAssembler;
import com.openclassrooms.safetynetalerts.dto.FirestationDTO;
import com.openclassrooms.safetynetalerts.entity.Firestation;
import com.openclassrooms.safetynetalerts.service.FirestationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.openclassrooms.safetynetalerts.utils.Utils.asJson;

@Slf4j
@RestController
@AllArgsConstructor
public class FirestationController {

    private final FirestationService firestationService;
    private final FirestationAssembler firestationAssembler;

    @PostMapping(value = "/firestation")
    public ResponseEntity<List<Firestation>> addFirestation(@RequestBody final FirestationDTO firestationDTO) {
        log.info("Call to addFirestation with object : " + asJson(firestationDTO));
        final var firestationAdded = firestationAssembler.toEntity(firestationDTO);
        log.info("New firestation successfully added");
        return new ResponseEntity<>(firestationService.save(firestationAdded), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/firestation")
    public ResponseEntity<Void> deleteFirestation(@RequestParam(value = "address") final String address,
                                                  @RequestParam(value = "station") final String station) {
        log.info("Call to deleteFirestation with address : " + asJson(address) + (" and station number : ") + asJson(station));
        firestationService.delete(address, station);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/firestations")
    public ResponseEntity<List<Firestation>> findAll() {
        log.info("Call to findAll");
        return new ResponseEntity<>(firestationService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/firestationInfo/address", produces = "application/json")
    public ResponseEntity<List<Firestation>> findByAddress(@RequestParam(value = "address") final String address) {
        log.info("Call to findByAddress with address : " + asJson(address));
        return new ResponseEntity<>(firestationService.findByAddress(address), HttpStatus.OK);
    }
    @GetMapping(value = "/firestationInfo/station", produces = "application/json")
    public ResponseEntity<List<Firestation>> findByStation(@RequestParam(value = "station") final String station) {
        log.info("Call to findByStation with number : " + asJson(station));
        return new ResponseEntity<>(firestationService.findByStation(station), HttpStatus.OK);
    }

    @PutMapping(value = "/firestation")
    public ResponseEntity<List<Firestation>> updateFirestation(@RequestBody final FirestationDTO firestationDTO) {
        log.info("Call to updateFirestation with object : " + asJson(firestationDTO));
        final var firestationUpdated = firestationAssembler.toEntity(firestationDTO);
        return new ResponseEntity<>(firestationService.update(firestationUpdated), HttpStatus.CREATED);
    }
}
