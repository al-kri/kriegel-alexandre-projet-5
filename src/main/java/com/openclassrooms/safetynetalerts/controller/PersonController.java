package com.openclassrooms.safetynetalerts.controller;

import com.openclassrooms.safetynetalerts.assembler.PersonAssembler;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;
import com.openclassrooms.safetynetalerts.model.PersonInfo;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;
import com.openclassrooms.safetynetalerts.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonController {

    private final PersonAssembler personAssembler;
    private final PersonService personService;
    private final MedicalRecordService medicalRecordService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> findAll() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/personInfo", produces = "application/json")
    public ResponseEntity<List<PersonInfo>> findByFirstNameAndLastName(@RequestParam(value = "firstName") final String firstName,
                                                                       @RequestParam(value = "lastName") final String lastName) {
        List<Person> person = personService.findByFirstNameAndLastName(firstName, lastName);
        List<MedicalRecord> medicalRecords = medicalRecordService.findByFirstNameAndLastName(firstName, lastName);
        List<PersonInfo> personInfoList = personAssembler.findByFirstNameAndLastNameModel(person, medicalRecords);
        return new ResponseEntity<>(personInfoList, HttpStatus.OK);
    }
}