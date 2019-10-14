package com.sg.surveyShrike.rest;

import com.sg.surveyShrike.domain.payload.BusinessPayload;
import com.sg.surveyShrike.service.BusinessManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_BAD_GATEWAY;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
@RequestMapping("/api/v1/business")
@Api(value = "/api/v1/business", tags = "Business Management Controller")
@Slf4j
public class BusinessManagementController {

    @Autowired
    private BusinessManagementService businessManagementService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch all hosted businesses", httpMethod = "GET", response = List.class)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "All hosted businesses fetched successfully"),
            @ApiResponse(code = SC_BAD_GATEWAY, message = "Error in fetching hosted businesses")
    })
    public List<BusinessPayload> getAllHostedBusinesses() {
        return businessManagementService.fetchAllHostedBusinesses();
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create or Modify businesses", httpMethod = "POST", response = String.class)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "Business saved successfully"),
            @ApiResponse(code = SC_BAD_GATEWAY, message = "Error in saving business")
    })
    public ResponseEntity<String> createOrUpdateBusiness(@RequestBody BusinessPayload businessPayload) {
        return businessManagementService.saveBusiness(businessPayload);
    }

    @GetMapping(path = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch businesses by admin", httpMethod = "GET", response = List.class)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "Business fetched successfully"),
            @ApiResponse(code = SC_BAD_GATEWAY, message = "Error in fetching businesse")
    })
    public BusinessPayload getBusinessByAdminUser(@RequestParam(value="email") String email) {
        return businessManagementService.fetchBusinessByUserId(email);
    }


}
