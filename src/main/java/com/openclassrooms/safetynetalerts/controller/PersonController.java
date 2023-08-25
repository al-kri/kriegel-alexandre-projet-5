package com.openclassrooms.safetynetalerts.controller;

import com.openclassrooms.safetynetalerts.assembler.PersonAssembler;
import com.openclassrooms.safetynetalerts.dto.PersonDTO;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;
import com.openclassrooms.safetynetalerts.model.PersonInfo;
import com.openclassrooms.safetynetalerts.service.MedicalRecordService;
import com.openclassrooms.safetynetalerts.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/person")
    public ResponseEntity<List<Person>> addPerson(@RequestBody PersonDTO personDTO) {
        final var personAdded = personAssembler.toEntity(personDTO);
        return new ResponseEntity<>(personService.save(personAdded),HttpStatus.CREATED);
    }

    @PutMapping(value = "/person")
    public ResponseEntity<List<Person>> updatePerson(@RequestBody PersonDTO personDTO) {
        final var personUpdated = personAssembler.toEntity(personDTO);
        return new ResponseEntity<>(personService.update(personUpdated),HttpStatus.CREATED);
    }
}