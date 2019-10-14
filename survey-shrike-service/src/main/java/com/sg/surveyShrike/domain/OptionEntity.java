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
@Table(name = "T_OPTION_SS")
public class OptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "optionSequence")
    @SequenceGenerator(name = "optionSequence", sequenceName = "S_OPTION_SS", allocationSize = 1)
    @Column(name = "PK_OPTION_ID", unique = true, nullable = false)
    private Long optionId;

    @Column(name = "TITLE", nullable = false)
    private String optionTitle;

    @ManyToOne
    @JoinColumn(name = "FK_QUESTION_ID")
    private QuestionEntity question;

    @Column(name = "ORDER")
    private Integer order;
}
