package com.plash.configurator.repository;

import com.plash.configurator.model.UserPersonaMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPersonaMappingRepository extends JpaRepository<UserPersonaMapping,Integer> {

    List<UserPersonaMapping> findByUserId(Long userId);
}
