package com.openclassrooms.safetynetalerts.controller;

import com.openclassrooms.safetynetalerts.assembler.MedicalRecordAssembler;
import com.openclassrooms.safetynetalerts.dto.MedicalRecordDTO;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;
    private final MedicalRecordAssembler medicalRecordAssembler;

    @PostMapping(value = "/medicalRecord")
    public ResponseEntity<List<MedicalRecord>> addMedicalRecord(@RequestBody final MedicalRecordDTO medicalRecordDTO) {
        final var medicalRecordAdded = medicalRecordAssembler.toEntity(medicalRecordDTO);
        return new ResponseEntity<>(medicalRecordService.save(medicalRecordAdded), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/medicalRecord")
    public ResponseEntity<Void> deleteMedicalRecord(@RequestParam(value = "firstName") final String firstName,
                                                    @RequestParam(value = "lastName") final String lastName) {
        medicalRecordService.delete(firstName, lastName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/medicalRecords")
    public ResponseEntity<List<MedicalRecord>> findAll() {
        return new ResponseEntity<>(medicalRecordService.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "medicalRecord")
    public ResponseEntity<List<MedicalRecord>> updateMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        final var medicalRecordUpdated = medicalRecordAssembler.toEntity(medicalRecordDTO);
        return new ResponseEntity<>(medicalRecordService.update(medicalRecordUpdated), HttpStatus.OK);
    }

}
