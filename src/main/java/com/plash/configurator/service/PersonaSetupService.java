package com.plash.configurator.service;

import com.plash.configurator.constants.Constants;
import com.plash.configurator.model.*;
import com.plash.configurator.pojo.*;
import com.plash.configurator.repository.*;
import com.plash.configurator.utils.AtomicIdCounter;
import com.plash.configurator.utils.StrUtil;
import com.plash.configurator.utils.emailsender.SendGridMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class PersonaSetupService implements Constants {

    @Autowired
    private PersonaSetupRepository personaSetupRepository;
    @Autowired
    private PersonaQuestionsRepository personaQuestionsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PersonaAnswersRepository personaAnswersRepository;
    @Autowired
    private PersonaTrackingRepository personaTrackingRepository;
    @Autowired
    private UserPersonaMappingRepository userPersonaMappingRepository;
    @Autowired
    private EmailTemplatesRepository emailTemplatesRepository;

    //save method to save persona details
    public ResponseCodeJson savePersona(PersonaSetupJson personaSetupJson) {
        Long userId = personaSetupJson.getUserId();
        Integer stepNo = personaSetupJson.getStepNo();
        Long companyId = personaSetupJson.getCompanyId();
        PersonaTracking personaTracking = new PersonaTracking();
        UserPersonaMapping userMapping = new UserPersonaMapping();
        // checking whether question is exists or not from questions table
        PersonaQuestions pq = personaQuestionsRepository.findByQuestionIdAndStepNumber(personaSetupJson.getQuestionId(), stepNo);
        if (pq != null) {
            // checking whether user id ,company id  with particular step number exists or not from persona setup table
            PersonaSetup personaSetup = personaSetupRepository.findByUserIdAndCompanyIdAndStepNumberAndDeleted(userId, companyId, stepNo, ZERO);
            if (personaSetup == null) {
                personaSetup = new PersonaSetup();
            }
            personaSetup.setUserId(userId);
            personaSetup.setCompanyId(companyId);
            personaSetup.setStepNumber(stepNo);
            personaSetup.setQuestionId(personaSetupJson.getQuestionId());
            personaSetup.setAnswerId(personaSetupJson.getAnswerId());
            personaSetup.setAnswer(personaSetupJson.getAnswer());
            personaSetup.setEmailId(personaSetupJson.getEmailId());
            personaSetup.setPersonaSetupId(AtomicIdCounter.getUniqueID());
            personaSetup.setQuestionType(personaSetupJson.getQuestionType());
            personaSetup.setAnswerType(personaSetupJson.getAnswerType());
            personaSetup.setDeleted(ZERO);
            personaSetupRepository.save(personaSetup);

            // saving details for persona tracking
            personaTracking.setPersonaTrackingId(AtomicIdCounter.getUniqueID());
            personaTracking.setUserId(personaSetupJson.getUserId());
            personaTracking.setLink(URL + personaSetup.getUserId() + FORWARDSLASH + personaSetup.getPersonaSetupId() + FORWARDSLASH + personaTracking.getPersonaTrackingId());
            personaTracking.setPersonaId(personaSetup.getPersonaSetupId());
            personaTrackingRepository.save(personaTracking);

            // saving details for persona user mapping
            userMapping.setPersonaId(personaSetup.getPersonaSetupId());
            userMapping.setUserMappingId(AtomicIdCounter.getUniqueID());
            userMapping.setUserId(personaSetup.getUserId());
            userPersonaMappingRepository.save(userMapping);
            return new ResponseCodeJson("Success", 200);
        } else {
            return new ResponseCodeJson("Question with this id not found", 400);
        }
    }

    // method to get user details from user table
    public UniversalResponse getUserInfo(IdOnly idOnly) {
        UniversalResponse<Users> ur = new UniversalResponse<>();
        // call to get users details from users table based on user id,company id,deleted
        Users users = usersRepository.findByUseridAndCompanyidAndDeleted(idOnly.getUserid(), idOnly.getCompanyid(), ZERO);
        if (users != null) {
            ur.setResponseCodeJson(new ResponseCodeJson("Success", 200));
            ur.setObject(users);
            return ur;
        } else {
            ur.setResponseCodeJson(new ResponseCodeJson("user not found with this id", 402));
            return ur;
        }
    }

    //method to get single question and answer
    public UniversalResponse getSingleQuestionWithAnswer(PersonaQuestionsJson pqj) {
        List<Object> listToAdd = new ArrayList<>();
        UniversalResponse ur = new UniversalResponse();
        // getting question based on question id,step number
        PersonaQuestions personaQuestions = personaQuestionsRepository.findByQuestionIdAndStepNumber(pqj.getQuestionId(), pqj.getStepNo());
        if (personaQuestions != null) {
            // getting answer based on question id
            List<PersonaAnswers> answersList = personaAnswersRepository.findByQuestionId(pqj.getQuestionId());
            if (answersList != null) {
                listToAdd.add(answersList);
            }
            listToAdd.add(personaQuestions);
            ur.setList(listToAdd);
            ur.setResponseCodeJson(new ResponseCodeJson("Success", 200));
            return ur;
        } else {
            ur.setResponseCodeJson(new ResponseCodeJson("Question details not found with this id", 404));
            return ur;
        }
    }

    //method for sending mail
    public ResponseCodeJson sendmail(PersonaSetupJson personaSetupJson) {
        Users users = usersRepository.findByUseridAndDeleted(personaSetupJson.getUserId(), ZERO);
        if (users != null) {
            List<PersonaSetup> personaSetupList = personaSetupRepository.findByuserIdAndDeleted(personaSetupJson.getUserId(), ZERO);
            if (personaSetupList != null) {
                String email = personaSetupJson.getEmailId() + "," + users.getUseremailid();
                EmailTemplates emailTemplates = emailTemplatesRepository.findFirstByName(PERSONASUCCESSFULMAIL);
                personaSetupList.sort(Comparator.comparing(PersonaSetup::getStepNumber));
                String subject = StrUtil.nonNull(emailTemplates.getSubject());
                String htmlbody = emailTemplates.getTemplate();
                Map<String, String> map = new LinkedHashMap<>();
                String personaName = "";
                if (personaSetupList.get(1).getStepNumber().equals(2)) {
                    personaName = personaSetupList.get(1).getAnswer();
                }
                int i = 1;
                for (PersonaSetup p : personaSetupList) {
                    String question = "";
                    String ids = "";
                    String ansToAdd = "";
                    PersonaQuestions personaQuestions = personaQuestionsRepository.findByQuestionId(p.getQuestionId());
                    if (personaQuestions != null) {
                        if (personaQuestions.getStepNumber().equals(2) && personaSetupList.get(0).getStepNumber().equals(1)) {
                            question = i + "." + " " + personaSetupList.get(0).getAnswer() + personaQuestions.getQuestion();
                        } else {
                            if (personaQuestions.getStepNumber().equals(2)) {
                                question = i + "." + " " + personaQuestions.getQuestion().substring(personaQuestions.getQuestion().indexOf(",") + 1);
                            } else {
                                question = i + "." + " " + personaQuestions.getQuestion().replace("@PERSONANAME@", personaName);
                            }
                        }
                        if (p.getAnswerId() != null) {
                            ids = p.getAnswerId();
                            String[] array = ids.split(",");
                            for (int j = 0; j < array.length; j++) {
                                PersonaAnswers personaAnswers = personaAnswersRepository.findByAnswerId(array[j]);
                                if (personaAnswers != null) {
                                    if (ansToAdd != "") {
                                        if (personaAnswers.getAnswer().equalsIgnoreCase("Other")) {
                                            ansToAdd = ansToAdd + " , " + personaAnswers.getAnswer() + " = " + p.getAnswer();
                                        } else {
                                            ansToAdd = ansToAdd + " , " + personaAnswers.getAnswer();
                                        }
                                    } else {
                                        if (personaAnswers.getAnswer().equalsIgnoreCase("Other")) {
                                            ansToAdd = ansToAdd + personaAnswers.getAnswer() + " = " + p.getAnswer();
                                        } else {
                                            ansToAdd = ansToAdd + personaAnswers.getAnswer();
                                        }
                                    }
                                }
                            }
                            map.put(question, ansToAdd);
                        } else {
                            map.put(question, p.getAnswer());
                        }
                    }
                    i++;
                }
                String all = map.entrySet()
                        .stream()
                        .map(e -> e.getKey() + "<br><br>" + e.getValue())
                        .collect(Collectors.joining("<br><br>"));
                htmlbody = htmlbody.replace("@ALLQUESTIONS@", all);
                if (personaSetupList.get(0).getStepNumber().equals(1)) {
                    htmlbody = htmlbody.replace("@USERNAME@", personaSetupList.get(0).getAnswer());
                } else {
                    htmlbody = htmlbody.replace("@USERNAME@", users.getUsername());
                }
                SendGridMailSender.sendEmailUsingAPI(email, subject, htmlbody, EMPTYSTRING);
                return new ResponseCodeJson("Success", 200);
            }
        }
        return new ResponseCodeJson("user not found to send mail", 402);
    }
}