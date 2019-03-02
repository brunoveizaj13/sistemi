/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bruno
 */
@Entity
@Table(name = "PERSON_DETAILS")
@Getter @Setter
public class PersonDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NID")
    private String nid;
    @Column(name = "PATRONAGE")
    private BigInteger patronage;
    @Column(name = "PATRONAGE_INSTITUTION")
    private BigInteger patronageInstitution;
    @Column(name = "POI")
    private BigInteger poi;
    @Column(name = "MEMBER")
    private BigInteger member;
    @Column(name = "FIRST_TIME_VOTER")
    private BigInteger firstTimeVoter;
    @Column(name = "VOTING_2017")
    private BigInteger voting2017;
    @Column(name = "DAP")
    private BigInteger dap;
    @Column(name = "ECONOMIC_HELP")
    private BigInteger economicHelp;
    @Column(name = "SOCIAL_HOUSE")
    private BigInteger socialHouse;
    @Column(name = "STATUS_1")
    private BigInteger status1;
    @Column(name = "STATUS_2")
    private BigInteger status2;
    @Column(name = "STATUS_3")
    private BigInteger status3;
    @Column(name = "STATUS_4")
    private BigInteger status4;
    @Column(name = "STATUS_5")
    private BigInteger status5;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "PHONE")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;

   
}
