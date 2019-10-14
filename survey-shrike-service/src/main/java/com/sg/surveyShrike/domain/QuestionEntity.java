package com.sg.surveyShrike.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_QUESTION_SS")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questionSequence")
    @SequenceGenerator(name = "questionSequence", sequenceName = "S_QUESTION_SS", allocationSize = 1)
    @Column(name = "PK_QUESTION_ID", unique = true, nullable = false)
    private Long questionId;

    @Column(name = "QUESTION_LINE", nullable = false)
    private String questionLine;

    @Column(name = "TYPE", nullable = false)
    private Integer questionType;

    @OneToMany(mappedBy = "question")
    private List<OptionEntity> optionEntities;

    @ManyToOne
    @JoinColumn(name = "FK_SURVEY_ID")
    private SurveyEntity survey;

    @Column(name = "ORDER")
    private Integer order;

}
