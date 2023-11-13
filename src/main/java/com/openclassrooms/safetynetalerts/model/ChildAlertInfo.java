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
public class ChildAlertInfo {

    private List<Person> childrens;
    private List<Person> householdMembers;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person {
        private String firstName;
        private String lastName;
        private int age;
    }
}
