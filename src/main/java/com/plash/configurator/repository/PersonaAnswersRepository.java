package com.plash.configurator.repository;

import com.plash.configurator.model.PersonaAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaAnswersRepository extends JpaRepository<PersonaAnswers,Long>{

   @Query(value = "SELECT * FROM PERSONAANSWERS Q WHERE Q.FQUESTIONID=?1",nativeQuery = true)
   List<PersonaAnswers> findByQuestionId(Long QuestionId);
   PersonaAnswers findByAnswerId(String answerId);
}
