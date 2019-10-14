package com.sg.surveyShrike.service;

import com.sg.surveyShrike.domain.SurveyEntity;
import com.sg.surveyShrike.domain.payload.SurveyPayload;
import com.sg.surveyShrike.repository.SurveyRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SurveyManagementServiceMockTest {
    @InjectMocks
    private SurveyManagementService surveyManagementService;
    @Mock
    private SurveyRepository surveyRepository;
    @Mock
    private ModelMapper modelMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(surveyManagementService, "surveyRepository", surveyRepository);
        ReflectionTestUtils.setField(surveyManagementService, "modelMapper", modelMapper);
    }

    @Test
    public void should_fetch_survey_details() {
        Mockito.when(surveyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(SurveyEntity.builder().name("Test Survey").build()));
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(SurveyPayload.builder().name("Test Survey").build());
        SurveyPayload fetchedSurveyPayload = surveyManagementService.fetchSurveyDetails(1L);
        Assertions.assertThat(fetchedSurveyPayload.getName()).isEqualTo("Test Survey");
    }
}
