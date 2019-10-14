package com.sg.surveyShrike.repository;

import com.sg.surveyShrike.domain.BusinessEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends CrudRepository<BusinessEntity, Long> {

    List<BusinessEntity> findAll();
}
