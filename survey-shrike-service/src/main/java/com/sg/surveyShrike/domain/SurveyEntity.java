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
@Table(name = "T_SURVEY_SS")
public class SurveyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "surveySequence")
    @SequenceGenerator(name = "surveySequence", sequenceName = "S_SURVEY_SS", allocationSize = 1)
    @Column(name = "PK_SURVEY_ID", unique = true, nullable = false)
    private Long surveyId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "FK_BUSINESS_ID")
    private BusinessEntity business;

    @OneToMany(mappedBy = "survey", fetch = FetchType.EAGER)
    private List<QuestionEntity> questions;
}
