package com.openclassrooms.safetynetalerts.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Utils {

    public static int getAgeFromBirthdate(final String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedBirthdate = LocalDate.parse(birthdate, formatter);
        LocalDate currentDate = LocalDate.now();
        return Period.between(parsedBirthdate, currentDate).getYears();
    }

    public static boolean isMinor(final String birthdate) {
        return getAgeFromBirthdate(birthdate) <= 18;
    }
}
