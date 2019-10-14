package com.sg.surveyShrike.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN(0), BUSINESS_USER(1), CUSTOMER(2);

    private int role;
}
