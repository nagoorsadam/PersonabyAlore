package com.plash.configurator.controller;

import com.plash.configurator.constants.Constants;
import com.plash.configurator.exception.ExceptionThrower;
import com.plash.configurator.pojo.PersonaSetupJson;
import com.plash.configurator.pojo.ResponseCodeJson;
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
@RequestMapping("/personamail")
public class PersonaMailSenderController implements Constants {

    static final Logger logger = LoggerFactory.getLogger(PersonaMailSenderController.class);

    @Autowired
    private PersonaSetupService personaSetupService;

    @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody PersonaSetupJson personaSetupJson) throws Exception {
        ExceptionThrower et = new ExceptionThrower();
        logger.info("Email id is: " + personaSetupJson.getEmailId());
        ResponseCodeJson rc = personaSetupService.sendmail(personaSetupJson);
        if (rc.getErrorcode() != 200) {
            et.throwCustomException(rc.getErrorcode(), rc.getMessage());
        }
        return new ResponseEntity<>(rc, HttpStatus.OK);
    }
}