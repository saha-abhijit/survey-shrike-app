package com.sg.surveyShrike.service;

import com.sg.surveyShrike.domain.BusinessAdminEntity;
import com.sg.surveyShrike.domain.BusinessEntity;
import com.sg.surveyShrike.domain.UserEntity;
import com.sg.surveyShrike.domain.payload.BusinessAdminPayload;
import com.sg.surveyShrike.domain.payload.BusinessPayload;
import com.sg.surveyShrike.repository.BusinessRepository;
import com.sg.surveyShrike.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Base64;
import java.util.List;

@Service("RegistrationService")
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<String> registerBusinessAdmin(BusinessAdminPayload businessAdminPayload) {
        //construct user
        UserEntity user = null;
        BusinessEntity business = null;
        List<UserEntity> usersWithEmailID = userRepository.findByEmail(businessAdminPayload.getEmail());
        if (CollectionUtils.isEmpty(usersWithEmailID)) {
            user = UserEntity.builder()
                    .email(businessAdminPayload.getEmail())
                    .firstName(businessAdminPayload.getFirstName())
                    .lastName(businessAdminPayload.getLastName())
                    .build();
            user = userRepository.save(user);
        } else {
            //user already exists. Return with message
        }
        if(null != businessAdminPayload.getBusinessId()) {
            //business exists. Add Admin
            business = businessRepository.findById(businessAdminPayload.getBusinessId()).orElse(null);
            createNewBusinessAdminUser(businessAdminPayload, user, business);
        } else {
            //construct business and create admin
            BusinessPayload businessPayload = BusinessPayload.builder().name(businessAdminPayload.getBusinessName()).address(businessAdminPayload.getBusinessAddress()).phoneNumber(businessAdminPayload.getBusinessPhone()).build();
            business = businessRepository.save(modelMapper.map(businessPayload, BusinessEntity.class));
            createNewBusinessAdminUser(businessAdminPayload, user, business);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    private void createNewBusinessAdminUser(BusinessAdminPayload businessAdminPayload, UserEntity user, BusinessEntity business) {
        if (null != business && null != user) {
            String userAuth = businessAdminPayload.getEmail().concat(":").concat(businessAdminPayload.getPassword());
            BusinessAdminEntity businessAdmin = BusinessAdminEntity.builder().
                    business(business)
                    .userEntity(user)
                    .password(Base64.getEncoder().encodeToString(userAuth.getBytes()))
                    .build();
            business.getBusinessAdmins().add(businessAdmin);
            businessRepository.save(business);
        }
    }
}
