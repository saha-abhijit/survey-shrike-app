package com.sg.surveyShrike.repository;

import com.sg.surveyShrike.domain.BusinessAdminEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessAdminRepository extends CrudRepository<BusinessAdminEntity, Long> {

    List<BusinessAdminEntity> findByUserEmail(String email);

}
