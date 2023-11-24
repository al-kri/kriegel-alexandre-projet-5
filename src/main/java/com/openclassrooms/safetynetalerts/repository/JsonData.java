package com.openclassrooms.safetynetalerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.entity.Firestation;
import com.openclassrooms.safetynetalerts.entity.JsonDataSource;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class JsonData {
    private static final String DATA_FILE_PATH = "src/main/resources/data.json";

    protected static List<Person> persons = new ArrayList<>();
    protected static List<MedicalRecord> medicalRecords = new ArrayList<>();
    protected static List<Firestation> firestations = new ArrayList<>();

    public static void loadJsonData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonDataSource jsonData = objectMapper.readValue(new File(DATA_FILE_PATH), JsonDataSource.class);
            persons = jsonData.getPersons();
            medicalRecords = jsonData.getMedicalRecords();
            firestations = jsonData.getFirestations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
