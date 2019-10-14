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
@Table(name = "T_BUSINESS_SS")
public class BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "businessSequence")
    @SequenceGenerator(name = "businessSequence", sequenceName = "S_BUSINESS_SS", allocationSize = 1)
    @Column(name = "PK_BUSINESS_ID", unique = true, nullable = false)
    private Long businessId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "PHONE", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private List<SurveyEntity> surveys;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private List<BusinessAdminEntity> businessAdmins;

}
