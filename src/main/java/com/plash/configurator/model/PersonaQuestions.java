package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PERSONAQUESTIONS" ,uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" })})
public class PersonaQuestions implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUESTIONID")
    private Long questionId;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "STEPNUMBER")
    private Integer stepNumber;

    @Column(name = "QUESTIONTYPE")
    private Integer questionType;

}
