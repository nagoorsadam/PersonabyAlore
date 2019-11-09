package com.plash.configurator.repository;

import com.plash.configurator.model.PersonaQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaQuestionsRepository extends JpaRepository<PersonaQuestions,Long>{

    @Query(value = "SELECT * FROM PERSONAQUESTIONS P WHERE P.QUESTIONID=?1 AND P.STEPNUMBER=?2",nativeQuery = true)
    PersonaQuestions findByQuestionIdAndStepNumber(Long questionId, Integer stepNumber);
    PersonaQuestions findByQuestionId(Long questionId);
}
