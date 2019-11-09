package com.plash.configurator.repository;

import com.plash.configurator.model.PersonaTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaTrackingRepository extends JpaRepository<PersonaTracking ,Integer> {

       List<PersonaTracking> findByUserId(Long userId);
}
