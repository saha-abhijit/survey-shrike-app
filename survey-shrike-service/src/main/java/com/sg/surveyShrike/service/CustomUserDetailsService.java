package com.sg.surveyShrike.service;

import com.sg.surveyShrike.domain.BusinessAdminEntity;
import com.sg.surveyShrike.domain.payload.UserPayload;
import com.sg.surveyShrike.repository.BusinessAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BusinessAdminRepository businessAdminRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ResponseEntity<UserPayload> userPayloadResponse =  restTemplate.postForEntity("http://localhost:8084/api/v1/auth/user",userName, UserPayload.class);
        UserPayload userPayload = userPayloadResponse.getBody();
        return null != userPayload ? new org.springframework.security.core.userdetails.User(userPayload.getEmail(), "",
                getAuthorities(userPayload)) : null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserPayload user) {
        List<String> roles;
        String[] userRoles;
        List<BusinessAdminEntity> businessAdmins = businessAdminRepository.findByUserEmail(user.getEmail());
        if(!CollectionUtils.isEmpty(businessAdmins)) {
            roles = Collections.singletonList("BUSINESS_ADMIN");
        } else {
            roles = Collections.singletonList("CUSTOMER");
        }
        userRoles = roles.toArray(new String[0]);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

}
