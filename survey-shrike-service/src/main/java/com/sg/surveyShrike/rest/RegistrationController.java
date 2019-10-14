package com.sg.surveyShrike.rest;

import com.sg.surveyShrike.domain.payload.BusinessAdminPayload;
import com.sg.surveyShrike.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static javax.servlet.http.HttpServletResponse.SC_BAD_GATEWAY;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
@RequestMapping("/api/v1/register")
@Api(value = "/api/v1/register", tags = "User Registration Controller")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register User", httpMethod = "POST", response = String.class)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "User registration successfully"),
            @ApiResponse(code = SC_BAD_GATEWAY, message = "Error in user registration")
    })
    public ResponseEntity<String> registerUser(@RequestBody BusinessAdminPayload businessAdminPayload) {
        return registrationService.registerBusinessAdmin(businessAdminPayload);
    }
}
