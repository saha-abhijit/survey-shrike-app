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
@Table(name = "T_BUSINESS_ADMIN_SS")
public class BusinessAdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "businessAdminSequence")
    @SequenceGenerator(name = "businessAdminSequence", sequenceName = "S_BUSINESS_ADMIN_SS", allocationSize = 1)
    @Column(name = "PK_BUSINESS_ADMIN_ID", unique = true, nullable = false)
    private Long businessAdminId;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "FK_BUSINESS_ID")
    private BusinessEntity business;

    @Column(name = "PASSWORD")
    private String password;
}
