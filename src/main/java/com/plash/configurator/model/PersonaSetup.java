package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "PERSONASETUP",uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" })})
public class PersonaSetup implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERID")
    private Long userId;

    @Column(name = "COMPANYID")
    private Long companyId;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "QUESTIONID")
    private Long questionId;

    @Column(name = "ANSWERID")
    private String answerId;

    @Column(name = "EMAILID")
    private String emailId;

    @Column(name = "PERSONASETUPID")
    private Long personaSetupId;

    @Column(name = "STEPNUMBER")
    private Integer stepNumber;

    @Column(name = "QUESTIONTYPE")
    private Integer questionType;

    @Column(name = "ANSWERTYPE")
    private Integer answerType;

    @Column(name = "DELETED")
    private Integer deleted;

}
