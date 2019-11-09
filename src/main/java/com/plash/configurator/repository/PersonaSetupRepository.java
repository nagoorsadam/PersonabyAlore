package com.plash.configurator.repository;

import com.plash.configurator.model.PersonaSetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaSetupRepository extends JpaRepository<PersonaSetup,Long> {

    PersonaSetup findByUserIdAndCompanyIdAndStepNumberAndDeleted(Long userId, Long companyId, Integer stepNumber, Integer deleted);
    List<PersonaSetup> findByuserIdAndDeleted(Long userId, Integer deleted);
}
