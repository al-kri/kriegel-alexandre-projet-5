package com.openclassrooms.safetynetalerts.IT;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CommunityControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getPersonsEmailByCity() throws Exception {
        final var url = "/communityEmail";
        final var mvcResult = mockMvc.perform(get(url).param("city", "culver"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final var result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<String>>() {
        });

        assertEquals(23, result.size());
        assertEquals("jaboyd@email.com", result.get(0));
        assertEquals("drk@email.com", result.get(1));
        assertEquals("tenz@email.com", result.get(2));
        assertEquals("jaboyd@email.com", result.get(3));
        assertEquals("jaboyd@email.com", result.get(4));
        assertEquals("drk@email.com", result.get(5));
        assertEquals("tenz@email.com", result.get(6));
        assertEquals("jaboyd@email.com", result.get(7));
        assertEquals("jaboyd@email.com", result.get(8));
        assertEquals("tcoop@ymail.com", result.get(9));
        assertEquals("lily@email.com", result.get(10));
        assertEquals("soph@email.com", result.get(11));
        assertEquals("ward@email.com", result.get(12));
        assertEquals("zarc@email.com", result.get(13));
        assertEquals("reg@email.com", result.get(14));
        assertEquals("jpeter@email.com", result.get(15));
        assertEquals("jpeter@email.com", result.get(16));
        assertEquals("aly@imail.com", result.get(17));
        assertEquals("bstel@email.com", result.get(18));
        assertEquals("ssanw@email.com", result.get(19));
        assertEquals("bstel@email.com", result.get(20));
        assertEquals("clivfd@ymail.com", result.get(21));
        assertEquals("gramps@email.com", result.get(22));
    }
}
