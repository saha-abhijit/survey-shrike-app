package com.sg.surveyShrike.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SUBMISSION_SS")
public class SubmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "submissionSequence")
    @SequenceGenerator(name = "submissionSequence", sequenceName = "S_SUBMISSION_SS", allocationSize = 1)
    @Column(name = "PK_SUBMISSION_ID", unique = true, nullable = false)
    private Long submissionId;

    @ManyToOne
    @JoinColumn(name = "FK_SURVEY_ID")
    private SurveyEntity survey;

    @ManyToOne
    @JoinColumn(name = "FK_QUESTION_ID")
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private UserEntity user;

    @Column(name = "ANSWER")
    private String answer;
}
