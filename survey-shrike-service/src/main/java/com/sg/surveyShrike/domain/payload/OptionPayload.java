package com.sg.surveyShrike.domain.payload;

import lombok.Data;

@Data
public class OptionPayload {

    private Long optionId;
    private String optionTitle;
    private QuestionPayload question;
    private Integer order;
}
