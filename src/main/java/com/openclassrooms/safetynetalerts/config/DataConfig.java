package com.openclassrooms.safetynetalerts.config;

import com.openclassrooms.safetynetalerts.repository.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DataConfig {

    public DataConfig() {
        log.info("Reading data.json on launch");
        JsonData.loadJsonData();
        log.info("Data loaded successfully");
    }
}
