package com.sg.surveyShrike.service;

import com.sg.surveyShrike.domain.BusinessEntity;
import com.sg.surveyShrike.domain.QuestionEntity;
import com.sg.surveyShrike.domain.SurveyEntity;
import com.sg.surveyShrike.domain.payload.SurveyPayload;
import com.sg.surveyShrike.repository.SurveyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("SurveyManagementService")
public class SurveyManagementService {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private ModelMapper modelMapper;

    public SurveyPayload fetchSurveyDetails(Long surveyId) {
        return surveyRepository.findById(surveyId).isPresent() ? modelMapper.map(surveyRepository.findById(surveyId).get(), SurveyPayload.class) : null;
    }

    public List<SurveyPayload> getAllSurveys() {
        return !CollectionUtils.isEmpty(surveyRepository.findAll()) ? surveyRepository.findAll().stream()
                .map(surveyEntity -> modelMapper.map(surveyEntity, SurveyPayload.class))
                .collect(Collectors.toList())
                : Collections.EMPTY_LIST;
    }

    public List<SurveyPayload> fetchSurveysByBusinessId(Long businessId) {
        return !CollectionUtils.isEmpty(surveyRepository.findAll()) ? surveyRepository.findByBusinessBusinessId(businessId).stream()
                .map(surveyEntity -> modelMapper.map(surveyEntity, SurveyPayload.class))
                .collect(Collectors.toList())
                : Collections.EMPTY_LIST;
    }

    public ResponseEntity<String> saveSurvey(SurveyPayload surveyPayload) {
        try {
            if (null == surveyPayload.getSurveyId()) {
                surveyRepository.save(modelMapper.map(surveyPayload, SurveyEntity.class));
                return new ResponseEntity<>("created", HttpStatus.OK);
            } else {
                surveyRepository.findById(surveyPayload.getSurveyId()).ifPresent(surveyEntity -> {
                    surveyEntity.setName(surveyPayload.getName());
                    surveyEntity.setBusiness(modelMapper.map(surveyPayload.getBusiness(), BusinessEntity.class));
                    surveyEntity.setQuestions(surveyPayload.getQuestions().stream()
                            .map(questionPayload -> modelMapper.map(questionPayload, QuestionEntity.class))
                            .collect(Collectors.toList()));
                    surveyRepository.save(surveyEntity);
                });
                return new ResponseEntity<>("updated", HttpStatus.OK);
            }
        }catch(Exception e ) {
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
    }


}

