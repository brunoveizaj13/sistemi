/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bruno
 */
@Entity
@Table(name = "PERSON")
@SecondaryTable(name="PERSON_DETAILS", pkJoinColumns = @PrimaryKeyJoinColumn(name="NID", referencedColumnName="NID"))
@Getter @Setter
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NID", insertable = false, updatable = false)
    private String nid;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 50)
    @Column(name = "SURNAME")
    private String surname;
    @Size(max = 50)
    @Column(name = "FATHER_NAME")
    private String fatherName;
    @Size(max = 50)
    @Column(name = "MOTHER_NAME")
    private String motherName;
    @Size(max = 50)
    @Column(name = "MAIDEN_NAME")
    private String maidenName;
    @Size(max = 15)
    @Column(name = "DOB")
    private String dob;
    @Size(max = 1)
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "FAMILY_ID")
    private BigInteger familyId;
    @Size(max = 100)
    @Column(name = "FAMILY_RELATION")
    private String familyRelation;
    @Column(name = "VOTING_NO")
    private Integer votingNo;
    @Size(max = 26)
    @Column(name = "FRACTION")
    private String fraction;
    @JoinColumn(name = "QV_ID", referencedColumnName = "ID")
    @ManyToOne
    private Qv qv;
    @Size(max = 500)
    @Column(name = "QV_ADDRESS")
    private String qvAddress;
    @Size(max = 50)
    @Column(name = "NAME_SX")
    private String nameSx;
    @Size(max = 50)
    @Column(name = "SURNAME_SX")
    private String surnameSx;
    @Size(max = 50)
    @Column(name = "FATHER_NAME_SX")
    private String fatherNameSx;
    @Size(max = 50)
    @Column(name = "MOTHER_NAME_SX")
    private String motherNameSx;
    @Size(max = 50)
    @Column(name = "MAIDEN_NAME_SX")
    private String maidenNameSx;
    @Size(max = 10)
    @Column(name = "SUBJECT_PARTY_CODE")
    private String subjectPartyCode;
    @Column(name = "SUBJECT_PARTY_USER_ID")
    private Integer subjectPartyUserId;
    @Column(name = "SUBJECT_PARTY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subjectPartyTime;

    @OneToOne
    @PrimaryKeyJoinColumn
    private PersonDetails details;
    
  //** SECOND_TABLE**//
    /*
    

    @Column(name = "PATRONAGE", table = "PERSON_DETAILS")
    private Integer patronageStatus;
    @Column(name = "PATRONAGE_INSTITUTION", table = "PERSON_DETAILS")
    private Integer patronageInstitutionStatus;
    @Column(name = "POI", table = "PERSON_DETAILS")
    private Integer poiStatus;
    @Column(name = "MEMBER", table = "PERSON_DETAILS")
    private Integer memberStatus;
    @Column(name = "FIRST_TIME_VOTER", table = "PERSON_DETAILS")
    private Integer firstTimeVoterStatus;
    @Column(name = "VOTING_2017", table = "PERSON_DETAILS")
    private Integer voting2017Status;
    @Column(name = "DAP", table = "PERSON_DETAILS")
    private Integer dapStatus;
    @Column(name = "ECONOMIC_HELP", table = "PERSON_DETAILS")
    private Integer economicHelpStatus;
    @Column(name = "SOCIAL_HOUSE", table = "PERSON_DETAILS")
    private Integer socialHouseStatus;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "PHONE", table = "PERSON_DETAILS")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL", table = "PERSON_DETAILS")
    private String email;
    */
}
