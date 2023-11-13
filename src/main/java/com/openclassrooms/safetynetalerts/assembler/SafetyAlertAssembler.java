package com.openclassrooms.safetynetalerts.assembler;

import com.openclassrooms.safetynetalerts.entity.Firestation;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;
import com.openclassrooms.safetynetalerts.model.ChildAlertInfo;
import com.openclassrooms.safetynetalerts.model.FireAlertInfo;
import com.openclassrooms.safetynetalerts.model.FirestationAlertInfo;
import com.openclassrooms.safetynetalerts.model.FloodAlertInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.openclassrooms.safetynetalerts.utils.Utils.getAgeFromBirthdate;

@Component
public class SafetyAlertAssembler {

    public List<ChildAlertInfo> toChildAlertInfoModel(String address, List<Person> personList, List<MedicalRecord> medicalRecords) {

        List<ChildAlertInfo> result = new ArrayList<>();

        List<ChildAlertInfo.Person> children = new ArrayList<>();
        List<ChildAlertInfo.Person> householdMembers = new ArrayList<>();

        for (Person person : personList) {
            if (person.getAddress().equalsIgnoreCase(address)) {
                MedicalRecord record = getMedicalRecordForPerson(person, medicalRecords);
                int age = getAgeFromBirthdate(record.getBirthdate());

                if (age <= 18) {
                    ChildAlertInfo.Person child = ChildAlertInfo.Person.builder()
                            .firstName(person.getFirstName())
                            .lastName(person.getLastName())
                            .age(age)
                            .build();
                    children.add(child);
                } else {
                    ChildAlertInfo.Person adult = ChildAlertInfo.Person.builder()
                            .firstName(person.getFirstName())
                            .lastName(person.getLastName())
                            .age(age)
                            .build();
                    householdMembers.add(adult);
                }
            }
        }

        if (!children.isEmpty()) {
            ChildAlertInfo childAlertInfo = ChildAlertInfo.builder()
                    .childrens(children)
                    .householdMembers(householdMembers)
                    .build();
            result.add(childAlertInfo);
        }
        return result;
    }

    private MedicalRecord getMedicalRecordByPersonForFAI(FirestationAlertInfo.Person person, List<MedicalRecord> medicalRecordList) {
        return medicalRecordList.stream()
                .filter(r -> r.getFirstName().equalsIgnoreCase(person.getFirstName())
                        && r.getLastName().equalsIgnoreCase(person.getLastName()))
                .findAny()
                .orElseThrow();
    }

    private MedicalRecord getMedicalRecordForPerson(Person person, List<MedicalRecord> medicalRecordList) {
        return medicalRecordList.stream()
                .filter(r -> r.getFirstName().equalsIgnoreCase(person.getFirstName())
                        && r.getLastName().equalsIgnoreCase(person.getLastName()))
                .findAny()
                .orElseThrow();
    }

    public List<FireAlertInfo> toFireAlertInfoModel(List<Person> personList, List<MedicalRecord> medicalRecordList, List<Firestation> firestationList) {
        return firestationList.stream()
                .flatMap(firestation -> personList.stream()
                        .filter(person -> person.getAddress().equalsIgnoreCase(firestation.getAddress()))
                        .map(person -> {
                            MedicalRecord medicalRecord = getMedicalRecordForPerson(person, medicalRecordList);
                            return FireAlertInfo.builder()
                                    .station(firestation.getStation())
                                    .lastName(person.getLastName())
                                    .phone(person.getPhone())
                                    .age(getAgeFromBirthdate(medicalRecord.getBirthdate()))
                                    .allergies(medicalRecord.getAllergies())
                                    .medication(medicalRecord.getMedications())
                                    .build();
                        }))
                .toList();
    }

    public List<FirestationAlertInfo> toFirestationAlertInfoModel(List<Person> personList, List<MedicalRecord> medicalRecordList, List<Firestation> firestationList) {

        final List<FirestationAlertInfo> result = new ArrayList<>();
        final int ADULT_AGE = 18;

        for (Firestation firestation : firestationList) {
            String station = firestation.getStation();
            List<Person> stationPersons = personList.stream()
                    .filter(person -> firestation.getAddress().equalsIgnoreCase(person.getAddress()))
                    .toList();

            List<FirestationAlertInfo.Person> mappedPersons = new ArrayList<>();
            for (Person person : stationPersons) {
                FirestationAlertInfo.Person mappedPerson = FirestationAlertInfo.Person.builder()
                        .firstName(person.getFirstName())
                        .lastName(person.getLastName())
                        .address(person.getAddress())
                        .phone(person.getPhone())
                        .build();
                mappedPersons.add(mappedPerson);
            }

            long numberOfAdults = mappedPersons.stream()
                    .filter(person -> getAgeFromBirthdate(getMedicalRecordByPersonForFAI(person, medicalRecordList).getBirthdate()) > ADULT_AGE)
                    .count();

            long numberOfChildren = mappedPersons.size() - numberOfAdults;

            FirestationAlertInfo firestationAlertInfo = FirestationAlertInfo.builder()
                    .station(station)
                    .persons(mappedPersons)
                    .numberOfAdults(numberOfAdults)
                    .numberOfChilds(numberOfChildren)
                    .build();
            result.add(firestationAlertInfo);
        }
        return result;
    }

    public List<FloodAlertInfo> toFloodAlertInfoModel(List<Person> personList, List<MedicalRecord> medicalRecordList, List<Firestation> firestationList) {
        return firestationList.stream()
                .map(firestation -> FloodAlertInfo.builder()
                        .address(firestation.getAddress())
                        .persons(personList.stream()
                                .filter(person -> person.getAddress().equalsIgnoreCase(firestation.getAddress()))
                                .flatMap(person -> medicalRecordList.stream()
                                        .filter(medicalRecord -> medicalRecord.getLastName().equalsIgnoreCase(person.getLastName()) && medicalRecord.getFirstName().equalsIgnoreCase(person.getFirstName()))
                                        .map(medicalRecord -> FloodAlertInfo.Person.builder()
                                                .lastName(person.getLastName())
                                                .phone(person.getPhone())
                                                .age(getAgeFromBirthdate(medicalRecord.getBirthdate()))
                                                .allergies(medicalRecord.getAllergies())
                                                .medication(medicalRecord.getMedications())
                                                .build()))
                                .toList())
                        .build()).toList();
    }
}