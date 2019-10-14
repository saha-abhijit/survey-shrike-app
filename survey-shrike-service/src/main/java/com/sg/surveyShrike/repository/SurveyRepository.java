package com.sg.surveyShrike.repository;

import com.sg.surveyShrike.domain.SurveyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends CrudRepository<SurveyEntity, Long> {

    List<SurveyEntity> findAll();
    List<SurveyEntity> findByBusinessBusinessId(Long businessId);
}

