package com.sg.surveyShrike.domain.payload;

import lombok.Data;

@Data
public class UserPayload {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
}
