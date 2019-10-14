package com.sg.surveyShrike.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyPayload {

    private Long surveyId;
    private String name;
    private BusinessPayload business;
    private List<QuestionPayload> questions;
}

