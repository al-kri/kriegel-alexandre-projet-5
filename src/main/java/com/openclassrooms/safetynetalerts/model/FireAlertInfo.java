package com.openclassrooms.safetynetalerts.model;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FireAlertInfo {

    private String station;
    private String lastName;
    private String phone;
    private int age;
    private List<String> allergies;
    private List<String> medication;

}
