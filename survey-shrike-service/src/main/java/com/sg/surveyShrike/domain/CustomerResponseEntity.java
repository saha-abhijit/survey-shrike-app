package com.sg.surveyShrike.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_CUSTOMER_RESPONSE_SS")
public class CustomerResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerResponseSequence")
    @SequenceGenerator(name = "customerResponseSequence", sequenceName = "S_CUSTOMER_RESPONSE_SS", allocationSize = 1)
    @Column(name = "PK_CUSTOMER_RESPONSE_ID", unique = true, nullable = false)
    private Long customerResponseId;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "FK_SURVEY_ID")
    private SurveyEntity survey;

    @ManyToOne
    @JoinColumn(name = "FK_QUESTION_ID")
    private QuestionEntity question;

    @Column(name = "RESPONSE")
    private String response;

}
