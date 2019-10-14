package com.sg.surveyShrike.service;

import com.sg.surveyShrike.domain.BusinessAdminEntity;
import com.sg.surveyShrike.domain.UserEntity;
import com.sg.surveyShrike.domain.payload.BusinessAdminPayload;
import com.sg.surveyShrike.domain.payload.UserAuthPayload;
import com.sg.surveyShrike.domain.payload.UserPayload;
import com.sg.surveyShrike.repository.BusinessAdminRepository;
import com.sg.surveyShrike.repository.UserRepository;
import com.sg.surveyShrike.service.mapper.BusinessAdminMapperService;
import com.sg.surveyShrike.service.mapper.UserMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Base64;
import java.util.List;

@Service("AuthorizationService")
@Slf4j
public class AuthorizationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessAdminRepository businessAdminRepository;
    @Autowired
    private BusinessAdminMapperService businessAdminMapperService;
    @Autowired
    private UserMapperService userMapperService;

    public ResponseEntity<UserPayload> getLoggedInUser(String email) {
        try {
            List<UserEntity> usersWithSpecifiedEmail = userRepository.findByEmail(email);
            UserPayload loggedInUser = !CollectionUtils.isEmpty(usersWithSpecifiedEmail) ? usersWithSpecifiedEmail.stream().map(user -> userMapperService.mapUserEntityToPayload(user)).findAny().orElse(null) : null;
            return null != loggedInUser ? new ResponseEntity<>(loggedInUser, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<BusinessAdminPayload> authorizeBusinessAdmin(UserAuthPayload userAuthPayload) {
        List<BusinessAdminEntity> businessAdminsWithSpecifiedEmail = businessAdminRepository.findByUserEmail(userAuthPayload.getEmail());
        BusinessAdminPayload authorizedBusinessAdmin = businessAdminsWithSpecifiedEmail.stream().filter(businessAdmin ->
            Base64.getDecoder().decode(businessAdmin.getPassword()).equals(userAuthPayload.getEmail().concat(userAuthPayload.getPassword()))).map(businessAdminEntity -> businessAdminMapperService.mapBusinessAdminToPayload(businessAdminEntity)).findAny().orElse(null);
        return null != authorizedBusinessAdmin ? new ResponseEntity<>(authorizedBusinessAdmin, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    /*public ResponseEntity<UsernamePasswordAuthenticationToken> authorizeRequest(UserAuthPayload userAuthPayload) {
        List<UserEntity> usersWithSpecifiedEmail = userRepository.findByEmail(userAuthPayload.getEmail());
        if(!CollectionUtils.isEmpty(usersWithSpecifiedEmail)) {
            UsernamePasswordAuthenticationToken authReq
                    = new UsernamePasswordAuthenticationToken(userAuthPayload.getEmail(), userAuthPayload.getPassword());
            return new ResponseEntity<>(authReq, HttpStatus.OK);
        }
    }*/




}
