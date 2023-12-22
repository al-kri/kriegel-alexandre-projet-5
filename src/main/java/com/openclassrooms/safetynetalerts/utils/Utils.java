package com.openclassrooms.safetynetalerts.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
@Slf4j
public abstract class Utils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static int getAgeFromBirthdate(final String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedBirthdate = LocalDate.parse(birthdate, formatter);
        LocalDate currentDate = LocalDate.now();
        return Period.between(parsedBirthdate, currentDate).getYears();
    }
    public static String asJson(Object dto) {
        try {
            return MAPPER.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }


}
