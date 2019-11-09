package com.plash.configurator.controller;

import com.plash.configurator.exception.ExceptionThrower;
import com.plash.configurator.pojo.*;
import com.plash.configurator.service.PersonaSetupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personasetup")
public class PersonaSetupController {

    private final static Logger logger = LoggerFactory.getLogger(PersonaSetupController.class);

    @Autowired
    private PersonaSetupService personaSetupService;

    @RequestMapping(value = "/savepersona", method = RequestMethod.POST)
    public ResponseEntity<?> savePersona(@RequestBody PersonaSetupJson personaSetupJson) throws Exception {
        logger.info("Persona setup details: " + personaSetupJson);
        ExceptionThrower et = new ExceptionThrower();
        ResponseCodeJson responseCodeJson = personaSetupService.savePersona(personaSetupJson);
        if (responseCodeJson.getErrorcode() != 200) {
            et.throwCustomException(responseCodeJson.getErrorcode(), responseCodeJson.getMessage());
        }
        return new ResponseEntity<>(responseCodeJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/userdetails", method = RequestMethod.POST)
    public ResponseEntity<?> getusersInfo(@RequestBody IdOnly idOnly) throws Exception {
        logger.info("User details: " + idOnly);
        ExceptionThrower et = new ExceptionThrower();
        UniversalResponse ur = personaSetupService.getUserInfo(idOnly);
        ResponseCodeJson responseCodeJson = ur.getResponseCodeJson();
        if (responseCodeJson.getErrorcode() != 200) {
            et.throwCustomException(responseCodeJson.getErrorcode(), responseCodeJson.getMessage());
        }
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }

    @RequestMapping(value = "/singlequestionwithanswer", method = RequestMethod.POST)
    public ResponseEntity<?> getSingleQuestionWithAnswer(@RequestBody PersonaQuestionsJson personaQuestionsJson) throws Exception {
        logger.info("Question id: " + personaQuestionsJson.getQuestionId());
        ExceptionThrower et = new ExceptionThrower();
        UniversalResponse ur = personaSetupService.getSingleQuestionWithAnswer(personaQuestionsJson);
        ResponseCodeJson responseCodeJson = ur.getResponseCodeJson();
        if (responseCodeJson.getErrorcode() != 200) {
            et.throwCustomException(responseCodeJson.getErrorcode(), responseCodeJson.getMessage());
        }
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
}