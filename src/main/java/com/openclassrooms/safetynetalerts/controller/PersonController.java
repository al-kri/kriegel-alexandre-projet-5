package com.openclassrooms.safetynetalerts.controller;

import com.openclassrooms.safetynetalerts.assembler.PersonAssembler;
import com.openclassrooms.safetynetalerts.dto.PersonDTO;
import com.openclassrooms.safetynetalerts.entity.Person;
import com.openclassrooms.safetynetalerts.service.PersonService;
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
public class PersonController {

    private final PersonAssembler personAssembler;
    private final PersonService personService;

    @PostMapping(value = "/person", produces = "application/json")
    public ResponseEntity<List<Person>> addPerson(@RequestBody PersonDTO personDTO) {
        log.info("Call to addPerson with object : " + asJson(personDTO));
        final var personAdded = personAssembler.toEntity(personDTO);
        List<Person> result = personService.save(personAdded);
        log.info("New person added in persons :" + asJson(result));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/person")
    public ResponseEntity<Void> deletePerson(@RequestParam(value = "firstName") final String firstName,
                                             @RequestParam(value = "lastName") final String lastName) {
        personService.delete(firstName, lastName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> findAll() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/person")
    public ResponseEntity<List<Person>> updatePerson(@RequestBody PersonDTO personDTO) {
        final var personUpdated = personAssembler.toEntity(personDTO);
        return new ResponseEntity<>(personService.update(personUpdated), HttpStatus.CREATED);
    }
}