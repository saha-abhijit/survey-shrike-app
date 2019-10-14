package com.sg.surveyShrike.rest;

import com.sg.surveyShrike.domain.payload.SurveyPayload;
import com.sg.surveyShrike.service.SurveyManagementService;
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
@RequestMapping("/api/v1/survey")
@Api(value = "/api/v1/survey", tags = "Survey Management Controller")
public class SurveyManagementController {

    @Autowired
    private SurveyManagementService surveyManagementService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch survey details", httpMethod = "GET", response = SurveyPayload.class)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "Surveys details fetched successfully"),
            @ApiResponse(code = SC_BAD_GATEWAY, message = "Error in fetching survey details")
    })
    public SurveyPayload fetchSurveyDetails(@RequestParam(value="surveyId") Long surveyId) {
        return surveyManagementService.fetchSurveyDetails(surveyId);
    }

    @GetMapping(path="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch all surveys", httpMethod = "GET", response = List.class)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "All surveys fetched successfully"),
            @ApiResponse(code = SC_BAD_GATEWAY, message = "Error in fetching surveys")
    })
    public List<SurveyPayload> fetchAllSurveys() {
        return surveyManagementService.getAllSurveys();
    }

    @GetMapping(path="/business", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch surveys for a business ID", httpMethod = "GET", response = List.class)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "Surveys fetched successfully"),
            @ApiResponse(code = SC_BAD_GATEWAY, message = "Error in fetching surveys")
    })
    public List<SurveyPayload> fetchSurveysByBusiness(@RequestParam(value = "businessId") Long businessId) {
        return surveyManagementService.fetchSurveysByBusinessId(businessId);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create or Modify survey", httpMethod = "POST", response = String.class)
    @ApiResponses({
            @ApiResponse(code = SC_OK, message = "Survey saved successfully"),
            @ApiResponse(code = SC_BAD_GATEWAY, message = "Error in saving survey")
    })
    public ResponseEntity<String> createOrUpdateBusiness(@RequestBody SurveyPayload surveyPayload) {
        return surveyManagementService.saveSurvey(surveyPayload);
    }


}
