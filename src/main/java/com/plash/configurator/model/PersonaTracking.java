package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PERSONATRACKING",uniqueConstraints = @UniqueConstraint(columnNames = {"ID"}))
public class PersonaTracking {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PERSONASETUPID")
    private Long personaTrackingId;

    @Column(name = "LINK")
    private String link;

    @Column(name = "USERID")
    private Long userId;

    @Column(name = "PERSONAID")
    private Long personaId;
}
