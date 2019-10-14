package com.sg.surveyShrike.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionType {

    MCQ(1), SINGLE_LINE(2), MULTI_LINE(3);

    private Integer type;
}
