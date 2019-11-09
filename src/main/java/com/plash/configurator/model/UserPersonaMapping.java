package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERPERSONAMAPPING", uniqueConstraints = @UniqueConstraint(columnNames = {"ID"}))
public class UserPersonaMapping {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USERID")
    private Long userId;

    @Column(name = "PERSONAID")
    private Long personaId;

    @Column(name = "USERMAPPINGID")
    private Long userMappingId;
}
