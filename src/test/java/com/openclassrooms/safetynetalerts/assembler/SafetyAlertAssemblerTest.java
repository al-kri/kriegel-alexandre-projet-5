package com.openclassrooms.safetynetalerts.assembler;

import com.openclassrooms.safetynetalerts.entity.Firestation;
import com.openclassrooms.safetynetalerts.entity.MedicalRecord;
import com.openclassrooms.safetynetalerts.entity.Person;
import com.openclassrooms.safetynetalerts.model.ChildAlertInfo;
import com.openclassrooms.safetynetalerts.model.FireAlertInfo;
import com.openclassrooms.safetynetalerts.model.FirestationAlertInfo;
import com.openclassrooms.safetynetalerts.model.FloodAlertInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SafetyAlertAssemblerTest {

    private SafetyAlertAssembler safetyAlertAssembler;

    @BeforeEach
    void beforeEach() {
        safetyAlertAssembler = new SafetyAlertAssembler();

    }

    @Test
    void toChildAlertInfoModel() {

        String address = "address";

        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder()
                .firstName("firstNameChild")
                .lastName("lastNameChild")
                .address("address")
                .build());
        personList.add(Person.builder()
                .firstName("firstNameAdult")
                .lastName("lastNameAdult")
                .address("address")
                .build());

        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(MedicalRecord.builder()
                .firstName("firstNameChild")
                .lastName("lastNameChild")
                .birthdate("06/15/2005")
                .build());
        medicalRecords.add(MedicalRecord.builder()
                .firstName("firstNameAdult")
                .lastName("lastNameAdult")
                .birthdate("06/15/1990")
                .build());

        List<ChildAlertInfo> result = safetyAlertAssembler.toChildAlertInfoModel(address, personList, medicalRecords);

        assertEquals(1, result.size());
        ChildAlertInfo childAlertInfo = result.get(0);
        assertEquals(1, childAlertInfo.getChildrens().size());
        assertEquals(1, childAlertInfo.getHouseholdMembers().size());

        ChildAlertInfo.Person child = childAlertInfo.getChildrens().get(0);
        assertEquals("firstNameChild", child.getFirstName());
        assertEquals("lastNameChild", child.getLastName());
        assertEquals(18, child.getAge());

        ChildAlertInfo.Person adult = childAlertInfo.getHouseholdMembers().get(0);
        assertEquals("firstNameAdult", adult.getFirstName());
        assertEquals("lastNameAdult", adult.getLastName());
        assertEquals(33, adult.getAge());
    }

    @Test
    void toFireAlertInfoModel() {

        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder()
                .firstName("firstNameChild")
                .lastName("lastNameChild")
                .address("123 Main St")
                .phone("123-456-7890")
                .build());
        personList.add(Person.builder()
                .firstName("firstNameAdult")
                .lastName("lastNameAdult")
                .address("456 Elm St")
                .phone("987-654-3210")
                .build());

        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(MedicalRecord.builder()
                .firstName("firstNameChild")
                .lastName("lastNameChild")
                .birthdate("06/15/2005")
                .allergies(List.of("Peanuts", "Fur"))
                .medications(List.of("Med1", "Med2"))
                .build());
        medicalRecordList.add(MedicalRecord.builder()
                .firstName("firstNameAdult")
                .lastName("lastNameAdult")
                .birthdate("06/15/1995")
                .allergies(List.of("None"))
                .medications(List.of("None"))
                .build());

        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(Firestation.builder()
                .address("123 Main St")
                .station("Station 1")
                .build());
        firestationList.add(Firestation.builder()
                .address("456 Elm St")
                .station("Station 2")
                .build());

        List<FireAlertInfo> result = safetyAlertAssembler.toFireAlertInfoModel(personList, medicalRecordList, firestationList);

        assertEquals(2, result.size());

        FireAlertInfo fireAlertInfo1 = result.get(0);
        assertEquals("Station 1", fireAlertInfo1.getStation());
        assertEquals("lastNameChild", fireAlertInfo1.getLastName());
        assertEquals("123-456-7890", fireAlertInfo1.getPhone());
        assertEquals(18, fireAlertInfo1.getAge());
        assertEquals(List.of("Peanuts", "Fur"), fireAlertInfo1.getAllergies());
        assertEquals(List.of("Med1", "Med2"), fireAlertInfo1.getMedication());

        FireAlertInfo fireAlertInfo2 = result.get(1);
        assertEquals("Station 2", fireAlertInfo2.getStation());
        assertEquals("lastNameAdult", fireAlertInfo2.getLastName());
        assertEquals("987-654-3210", fireAlertInfo2.getPhone());
        assertEquals(28, fireAlertInfo2.getAge());
        assertEquals(List.of("None"), fireAlertInfo2.getAllergies());
        assertEquals(List.of("None"), fireAlertInfo2.getMedication());
    }

    @Test
    void toFirestationAlertInfoModel() {

        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder()
                .firstName("firstNameChild")
                .lastName("lastNameChild")
                .address("123 Main St")
                .phone("123-456-7890")
                .build());
        personList.add(Person.builder()
                .firstName("firstNameAdult")
                .lastName("lastNameAdult")
                .address("456 Elm St")
                .phone("987-654-3210")
                .build());

        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(MedicalRecord.builder()
                .firstName("firstNameChild")
                .lastName("lastNameChild")
                .birthdate("06/15/2005")
                .build());
        medicalRecordList.add(MedicalRecord.builder()
                .firstName("firstNameAdult")
                .lastName("lastNameAdult")
                .birthdate("06/15/1995")
                .build());

        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(Firestation.builder()
                .address("123 Main St")
                .station("Station 1")
                .build());
        firestationList.add(Firestation.builder()
                .address("456 Elm St")
                .station("Station 2")
                .build());

        List<FirestationAlertInfo> result = safetyAlertAssembler.toFirestationAlertInfoModel(personList, medicalRecordList, firestationList);

        assertEquals(2, result.size());

        FirestationAlertInfo firestation1 = result.get(0);
        assertEquals("Station 1", firestation1.getStation());
        assertEquals(1, firestation1.getPersons().size());
        assertEquals(0, firestation1.getNumberOfAdults());
        assertEquals(1, firestation1.getNumberOfChilds());

        FirestationAlertInfo firestation2 = result.get(1);
        assertEquals("Station 2", firestation2.getStation());
        assertEquals(1, firestation2.getPersons().size());
        assertEquals(1, firestation2.getNumberOfAdults());
        assertEquals(0, firestation2.getNumberOfChilds());
    }

    @Test
    void toFloodAlertInfoModel() {

        List<Person> personList = new ArrayList<>();
        personList.add(Person.builder()
                .firstName("firstNameChild")
                .lastName("lastNameChild")
                .address("123 Main St")
                .phone("123-456-7890")
                .build());
        personList.add(Person.builder()
                .firstName("firstNameAdult")
                .lastName("lastNameAdult")
                .address("123 Main St")
                .phone("987-654-3210")
                .build());

        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(MedicalRecord.builder()
                .firstName("firstNameChild")
                .lastName("lastNameChild")
                .birthdate("06/15/2005")
                .allergies(List.of("Peanuts", "Fur"))
                .medications(List.of("Med1", "Med2"))
                .build());
        medicalRecordList.add(MedicalRecord.builder()
                .firstName("firstNameAdult")
                .lastName("lastNameAdult")
                .birthdate("06/15/1995")
                .allergies(List.of("None"))
                .medications(List.of("None"))
                .build());

        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(Firestation.builder()
                .address("123 Main St")
                .station("Station 1")
                .build());

        List<FloodAlertInfo> result = safetyAlertAssembler.toFloodAlertInfoModel(personList, medicalRecordList, firestationList);

        assertEquals(1, result.size());

        FloodAlertInfo floodAlertInfo = result.get(0);
        assertEquals("123 Main St", floodAlertInfo.getAddress());

        List<FloodAlertInfo.Person> persons = floodAlertInfo.getPersons();
        assertEquals(2, persons.size());

        FloodAlertInfo.Person person1 = persons.get(0);
        assertEquals("lastNameChild", person1.getLastName());
        assertEquals("123-456-7890", person1.getPhone());
        assertEquals(18, person1.getAge());
        assertEquals(List.of("Peanuts", "Fur"), person1.getAllergies());
        assertEquals(List.of("Med1", "Med2"), person1.getMedication());

        FloodAlertInfo.Person person2 = persons.get(1);
        assertEquals("lastNameAdult", person2.getLastName());
        assertEquals("987-654-3210", person2.getPhone());
        assertEquals(28, person2.getAge());
        assertEquals(List.of("None"), person2.getAllergies());
        assertEquals(List.of("None"), person2.getMedication());
    }
}