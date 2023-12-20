package com.openclassrooms.safetynetalerts.IT;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetalerts.model.ChildAlertInfo;
import com.openclassrooms.safetynetalerts.model.FireAlertInfo;
import com.openclassrooms.safetynetalerts.model.FirestationAlertInfo;
import com.openclassrooms.safetynetalerts.model.FloodAlertInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SafetyAlertControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void childAlert() throws Exception {
        final var url = "/childAlert";
        final var mvcResult = mockMvc.perform(get(url).param("address", "1509 Culver St"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<ChildAlertInfo>>() {
        });

        assertEquals(1, result.size());

        final var infoInList = result.get(0);
        assertEquals(2, infoInList.getChildrens().size());
        assertEquals(3, infoInList.getHouseholdMembers().size());

        final var firstChild = infoInList.getChildrens().get(0);
        assertEquals("Tenley", firstChild.getFirstName());
        assertEquals("Boyd", firstChild.getLastName());
        assertEquals(11, firstChild.getAge());

        final var secondChild = infoInList.getChildrens().get(1);
        assertEquals("Roger", secondChild.getFirstName());
        assertEquals("Boyd", secondChild.getLastName());
        assertEquals(6, secondChild.getAge());

        final var firstAdult = infoInList.getHouseholdMembers().get(0);
        assertEquals("John", firstAdult.getFirstName());
        assertEquals(39, firstAdult.getAge());

    }

    @Test
    void fireAlert() throws Exception {
        final var url = "/fire";
        final var mvcResult = mockMvc.perform(get(url).param("address", "1509 Culver St"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<FireAlertInfo>>() {
        });

        assertEquals(5, result.size());
        assertEquals("3", result.get(0).getStation());

        final var firstPersonInList = result.get(0);
        assertEquals("Boyd", firstPersonInList.getLastName());
        assertEquals("841-874-6512", firstPersonInList.getPhone());
        assertEquals(39, firstPersonInList.getAge());

        final var lastPersonInList = result.get(4);
        assertEquals("Boyd", lastPersonInList.getLastName());
        assertEquals("841-874-6544", lastPersonInList.getPhone());
        assertEquals(37, lastPersonInList.getAge());


    }

    @Test
    void firestationAlert() throws Exception {
        final var url = "/firestation";
        final var mvcResult = mockMvc.perform(get(url).param("station_number", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<FirestationAlertInfo>>() {
        });

        assertEquals(3, result.size());

        FirestationAlertInfo firstInfoInList = result.get(0);
        assertEquals(0, firstInfoInList.numberOfChilds);
        assertEquals(1, firstInfoInList.numberOfAdults);

        FirestationAlertInfo lastInfoInList = result.get(2);
        assertEquals(1, lastInfoInList.numberOfChilds);
        assertEquals(2, lastInfoInList.numberOfAdults);
    }

    @Test
    void floodAlert() throws Exception {
        final var url = "/flood/stations";
        final var mvcResult = mockMvc.perform(get(url)
                        .param("stations", "1")
                        .param("stations", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<FloodAlertInfo>>() {
        });

        assertEquals(6, result.size());

        final var firstInfoInList = result.get(0);
        assertEquals("644 Gershwin Cir", firstInfoInList.getAddress());

        final var secondInfoInList = result.get(1);
        assertEquals("908 73rd St", secondInfoInList.getAddress());

    }

    @Test
    void phoneAlert() throws Exception {
        final var url = "/phoneAlert";
        final var mvcResult = mockMvc.perform(get(url).param("firestation_number", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<String>>() {
        });

        assertEquals(6, result.size());
        assertEquals("841-874-6512", result.get(0));
        assertEquals("841-874-8547", result.get(1));
        assertEquals("841-874-7462", result.get(2));
        assertEquals("841-874-7784", result.get(3));
        assertEquals("841-874-7784", result.get(4));
        assertEquals("841-874-7784", result.get(5));
    }

}
