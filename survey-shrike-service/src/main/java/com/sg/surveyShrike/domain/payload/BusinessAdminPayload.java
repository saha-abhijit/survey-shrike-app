package com.sg.surveyShrike.domain.payload;

import lombok.Data;

@Data
public class BusinessAdminPayload {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long businessId;
    private String businessName;
    private String businessAddress;
    private String businessPhone;

}
