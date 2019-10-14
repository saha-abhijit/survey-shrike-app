package com.sg.surveyShrike.domain.payload;

import lombok.Data;

@Data
public class UserAuthPayload {

    private String email;
    private String password;
}
