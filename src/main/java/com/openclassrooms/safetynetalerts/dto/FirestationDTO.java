package com.openclassrooms.safetynetalerts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FirestationDTO {

    private String address;
    private String station;

}
