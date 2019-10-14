package com.sg.surveyShrike.rest;

import com.sg.surveyShrike.domain.payload.BusinessAdminPayload;
import com.sg.surveyShrike.domain.payload.UserAuthPayload;
import com.sg.surveyShrike.domain.payload.UserPayload;
import com.sg.surveyShrike.service.AuthorizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static javax.servlet.http.HttpServletResponse.*;

@RestController
@RequestMapping("/api/v1/auth")
@Api(value = "/api/v1/auth", tags = "Authorization Controller")
@Slf4j
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch the current role for the logged in user", httpMethod = "POST", response = UserPayload.class)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "User role fetched successfully"),
            @ApiResponse(code = SC_BAD_GATEWAY, message = "Error in fetching the logged in user role")
    })
    public ResponseEntity<UserPayload> fetchLoggedInUser(@RequestParam(value="email") String email) {
        log.info("Request to fetch role for email: " + email);
        return authorizationService.getLoggedInUser(email);
    }

    @PostMapping(path = "/authorize", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Authorize Business admin", httpMethod = "POST", response = BusinessAdminPayload.class)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "Authorization successfully"),
            @ApiResponse(code = SC_NO_CONTENT, message = "Unauthorized. User does not exist")
    })
    public ResponseEntity<BusinessAdminPayload> validateBusinessAdmin(@RequestBody UserAuthPayload userAuthPayload) {
        return authorizationService.authorizeBusinessAdmin(userAuthPayload);
    }

}
