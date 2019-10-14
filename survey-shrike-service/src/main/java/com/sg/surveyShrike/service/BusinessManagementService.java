package com.sg.surveyShrike.service;

import com.sg.surveyShrike.audit.AuditorAwareImpl;
import com.sg.surveyShrike.domain.BusinessAdminEntity;
import com.sg.surveyShrike.domain.BusinessEntity;
import com.sg.surveyShrike.domain.UserEntity;
import com.sg.surveyShrike.domain.payload.BusinessPayload;
import com.sg.surveyShrike.repository.BusinessAdminRepository;
import com.sg.surveyShrike.repository.BusinessRepository;
import com.sg.surveyShrike.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("BusinessManagementService")
@Slf4j
public class BusinessManagementService {

    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuditorAwareImpl auditorAware;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessAdminRepository businessAdminRepository;

    public List<BusinessPayload> fetchAllHostedBusinesses() {
        return !CollectionUtils.isEmpty(businessRepository.findAll()) ? businessRepository.findAll().stream()
                .map(businessEntity -> modelMapper.map(businessEntity, BusinessPayload.class))
                .collect(Collectors.toList())
                : Collections.EMPTY_LIST;
    }

    public ResponseEntity<String> saveBusiness(BusinessPayload businessPayload) {
        try {
            if (null == businessPayload.getBusinessId()) {
                businessRepository.save(modelMapper.map(businessPayload, BusinessEntity.class));
                return new ResponseEntity<>("created", HttpStatus.OK);
            } else {
                BusinessEntity business = businessRepository.findById(businessPayload.getBusinessId()).orElse(null);
                if (null != business) {
                    business.setName(businessPayload.getName());
                    business.setAddress(businessPayload.getAddress());
                    business.setPhoneNumber(businessPayload.getPhoneNumber());
                    businessRepository.save(business);
                }
                return new ResponseEntity<>("updated", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
    }

    public BusinessPayload fetchBusinessByUserId(String email) {
        List<BusinessAdminEntity> businessAdminRecords = businessAdminRepository.findByUserEmail(email);
        return !CollectionUtils.isEmpty(businessAdminRecords) ? businessAdminRecords.stream().findAny().
                map(businessAdmin -> modelMapper.map(businessAdmin.getBusiness(), BusinessPayload.class))
                .orElse(null) : null;
    }
}
