package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "EMAILTEMPLATES", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class EmailTemplates {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TEMPLATE")
    private String template;

    @Column(name = "SUBJECT")
    private String subject;

}
