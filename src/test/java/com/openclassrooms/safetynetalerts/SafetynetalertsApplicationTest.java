package com.openclassrooms.safetynetalerts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SafetynetalertsApplicationTest {

    @Test
    void main() {
        assertDoesNotThrow(() -> SafetynetalertsApplication.main(new String[]{}));
    }
}