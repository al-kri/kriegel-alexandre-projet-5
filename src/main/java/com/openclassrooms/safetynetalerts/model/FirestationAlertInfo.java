package com.openclassrooms.safetynetalerts.model;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FirestationAlertInfo {

    private String station;
    private List<Person> persons;
    @Setter
    public long numberOfChilds;
    @Setter
    public long numberOfAdults;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person {
        private String firstName;
        private String lastName;
        private String address;
        private String phone;
    }
}
