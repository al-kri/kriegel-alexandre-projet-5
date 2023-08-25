package com.openclassrooms.safetynetalerts.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class JsonDataSource {

    private List<Person> persons;
    private List<Firestation> firestations;
    @JsonProperty(value = "medicalrecords")
    private List<MedicalRecord> medicalRecords;

}
