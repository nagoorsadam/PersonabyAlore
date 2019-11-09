/**
 * 
 */
package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "USERS", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class Users {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERID")
	private Long userid;

	@Column(name = "COMPANYID")
	private Long companyid;

	@Column(name = "SOURCEID")
	private Long sourceid;

	@Column(name = "USEREMAILID")
	private String useremailid;

	@Column(name = "FIRSTNAME")
	private String firstname;

	@Column(name = "LASTNAME")
	private String lastname;

	@Column(name = "DESIGNATION")
	private String designation;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "DATEOFJOINING")
	private Date dateofjoining;

	@Column(name = "USERPHOTO")
	private String userphoto;

	@Column(name = "UPHONE")
	private String uphone;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "COMPANYWEBSITE")
	private String companywebsite;

	@Column(name = "COMPANYNAME")
	private String companyname;

	@Column(name = "TWITTERURL")
	private String twitterurl;

	@Column(name = "LINKEDINURL")
	private String linkedinurl;

	@Column(name = "FACEBOOKURL")
	private String facebookurl;

	@Column(name = "PINTRESTURL")
	private String pintresturl;

	@Column(name = "INSTAGRAMURL")
	private String instagramurl;

	@Column(name = "KLOUTURL")
	private String klouturl;

	@Column(name = "MEDIUMURL")
	private String mediumurl;

	@Column(name = "RSSFEEDURL")
	private String rssfeedurl;

	@Column(name = "CRUNCHBASEURL")
	private String crunchbaseurl;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PIN")
    private String pin;

    @Column(name = "VERIFIED")
	private Integer verified;

	@Column(name = "VERIFYDATE")
	private Date verifydate;

	@Column(name = "DELETED")
	private Integer deleted;

	//For role based access 1=Enabled, 0=Not Enabled
	@Column(name = "ENABLED")
	private Integer enabled;

	//For User type 1=Premium, 0=Normal
	@Column(name = "SUBSCRIPTIONTYPE", columnDefinition = "int default '1'")
	private Integer subscriptiontype;

	// for onboarding mails 1= enabled 0= disabled
	@Column(name = "ONBOARDING", columnDefinition = "int default '1'")
	private Integer onboarding;

	// 0 = not integrated & 1 = integrated & 2 = delete integration from crm
	@Column(name = "CALENDARINTEGARTION", columnDefinition = "int default '0'")
	private Integer calendarintegartion;

	@Column(name = "CALENDARURL")
	private String calendarurl;

	@Column(name = "PUBLICPROFILEURL")
	private String publicprofileurl;

	@Transient
	private Long refereeuserid;

	@Transient
	private Long referralcode;

	@Transient
	private Integer teamrole;

	@Transient
	private String currency;

	@Transient
	private String timezone;

	@Transient
	private String email;
}
