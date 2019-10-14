package com.sg.surveyShrike.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessPayload {

    private Long businessId;
    private String name;
    private String address;
    private String phoneNumber;

}
