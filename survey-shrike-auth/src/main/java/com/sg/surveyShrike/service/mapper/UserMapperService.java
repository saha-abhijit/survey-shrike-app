package com.sg.surveyShrike.service.mapper;

import com.sg.surveyShrike.domain.UserEntity;
import com.sg.surveyShrike.domain.payload.UserPayload;
import org.springframework.stereotype.Service;

@Service("UserMapperService")
public class UserMapperService {

    public UserPayload mapUserEntityToPayload(UserEntity userEntity) {
        return UserPayload.builder().firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).email(userEntity.getEmail()).userId(userEntity.getUserId()).build();
    }
}
