package com.plash.configurator.pojo;

import lombok.Data;

@Data
public class PersonaSetupJson {

    private Long userId;

    private Long companyId;

    private Long questionId;

    private String answerId;

    private String answer;

    private String emailId;

    private Integer stepNo;

    private Long personaSetupId;

    private Integer questionType;

    private Integer answerType;

}
