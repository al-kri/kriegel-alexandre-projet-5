package com.openclassrooms.safetynetalerts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FloodAlertInfo {

    private String address;
    private List<Person> persons;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person {
        private String lastName;
        private String phone;
        private int age;
        private List<String> allergies;
        private List<String> medication;
    }

}
