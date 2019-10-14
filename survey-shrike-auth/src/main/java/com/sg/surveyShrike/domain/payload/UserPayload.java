package com.sg.surveyShrike.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPayload {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
}
