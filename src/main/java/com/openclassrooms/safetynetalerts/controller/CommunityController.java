package com.openclassrooms.safetynetalerts.controller;

import com.openclassrooms.safetynetalerts.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.openclassrooms.safetynetalerts.utils.Utils.asJson;

@Slf4j
@RestController
@AllArgsConstructor
public class CommunityController {

    private final PersonService personService;
    @GetMapping(value = "/communityEmail")
    public ResponseEntity<List<String>> getPersonsEmailByCity(@RequestParam(value = "city") String city) {
        log.info("Call to getPersonsEmailByCity with :" + asJson(city));
        return new ResponseEntity<>(personService.getPersonsEmailByCity(city), HttpStatus.OK);
    }
}
