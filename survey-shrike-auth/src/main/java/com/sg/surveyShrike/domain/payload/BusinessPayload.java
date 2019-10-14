package com.sg.surveyShrike.domain.payload;

import lombok.Data;

@Data
public class BusinessPayload {

    private Long businessId;
    private String name;
    private String address;
    private Long phoneNumber;

}
