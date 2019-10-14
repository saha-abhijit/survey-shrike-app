package com.sg.surveyShrike.service.mapper;

import com.sg.surveyShrike.domain.BusinessAdminEntity;
import com.sg.surveyShrike.domain.payload.BusinessAdminPayload;
import org.springframework.stereotype.Service;

@Service
public class BusinessAdminMapperService {

    public BusinessAdminPayload mapBusinessAdminToPayload(BusinessAdminEntity businessAdminEntity) {
        return BusinessAdminPayload.builder().
                userId(businessAdminEntity.getUser().getUserId())
                .businessId(businessAdminEntity.getBusiness().getBusinessId())
                .firstName(businessAdminEntity.getUser().getFirstName())
                .lastName(businessAdminEntity.getUser().getLastName())
                .email(businessAdminEntity.getUser().getEmail()).build();
    }
}
