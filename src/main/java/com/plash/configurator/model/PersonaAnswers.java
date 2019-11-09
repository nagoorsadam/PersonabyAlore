package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "PERSONAANSWERS" ,uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" })})
public class PersonaAnswers {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "ANSWERID")
    private String answerId;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "ANSWERTYPE")
    private Integer answerType;

    @OneToOne
    @JoinColumn(name = "FQUESTIONID",referencedColumnName = "QUESTIONID")
    private PersonaQuestions personaQuestions;
}
