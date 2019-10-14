package com.sg.surveyShrike.domain.payload;

import lombok.Data;

import java.util.List;

@Data
public class QuestionPayload {

    private Long questionId;
    private String questionLine;
    private Integer questionType;
    private List<OptionPayload> optionEntities;
    private SurveyPayload survey;
    private Integer order;
}
