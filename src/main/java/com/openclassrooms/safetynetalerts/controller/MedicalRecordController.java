package com.openclassrooms.safetynetalerts.controller;

import com.openclassrooms.safetynetalerts.assembler.MedicalRecordAssembler;
import com.openclassrooms.safetynetalerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;
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
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;
    private final MedicalRecordAssembler medicalRecordAssembler;

    @PostMapping(value = "/medicalRecord")
    public ResponseEntity<List<MedicalRecord>> addMedicalRecord(@RequestBody final MedicalRecordDTO medicalRecordDTO) {
        log.info("Call to addMedicalRecord with object : " + asJson(medicalRecordDTO));
        final var medicalRecordAdded = medicalRecordAssembler.toEntity(medicalRecordDTO);
        log.info("MedicalRecord was successfully added");
        return new ResponseEntity<>(medicalRecordService.save(medicalRecordAdded), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/medicalRecord")
    public ResponseEntity<Void> deleteMedicalRecord(@RequestParam(value = "firstName") final String firstName,
                                                    @RequestParam(value = "lastName") final String lastName) {
        log.info("Call to deleteMedicalRecord with firstname : " + firstName + " and lastname : " + lastName);
        medicalRecordService.delete(firstName, lastName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/medicalRecords")
    public ResponseEntity<List<MedicalRecord>> findAll() {
        log.info("Call to findAll");
        return new ResponseEntity<>(medicalRecordService.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/medicalRecord")
    public ResponseEntity<List<MedicalRecord>> updateMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        log.info("Call to updateMedicalRecord with object : " + asJson(medicalRecordDTO));
        final var medicalRecordUpdated = medicalRecordAssembler.toEntity(medicalRecordDTO);
        return new ResponseEntity<>(medicalRecordService.update(medicalRecordUpdated), HttpStatus.OK);
    }

}
