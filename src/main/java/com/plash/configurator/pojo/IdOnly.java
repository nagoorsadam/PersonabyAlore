package com.plash.configurator.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Created by satish on 2/23/2017.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdOnly {
    private Long id;
    private Long companyid;
    private Long prospectid;
    private Long userid;
    private Long googlealiasid;
    private Long campaignid;
    private Long emailuniqueid;
    private Long linkid;
    private String messegeid;
    private Integer pageno;
    private Long employeeid;
    private Long clientid;
    private Long clientsecret;
    private String mode;
    private String transactionid;
    private String email;
    private String token;
    private String zoomToken;
    private String meetingId;
    private String webinarId;
    private String meetingType;
    private String tagname;
    private Long leadowner;
    private Long teammateid;
    private Long teamid;
    private Long invitationid;
    private Integer loginmode;
    private int statuscode;
    private String password;
    private String version;
    private List<Long> ownerlist;
    private Integer reqid;
    private String channelid;
    private String adminid;
    private String conversationid;
    private String message;
    private String realmId;
    private String color;
    private Long personaSetupId;
}
