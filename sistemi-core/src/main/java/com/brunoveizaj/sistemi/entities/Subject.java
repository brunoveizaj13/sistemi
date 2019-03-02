/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;
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
@Table(name = "SUBJECT")
@Getter @Setter
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 26)
    @Column(name = "NIPT")
    private String nipt;
    @Size(max = 500)
    @Column(name = "ADMINISTRATOR")
    private String administrator;
    @Size(max = 128)
    @Column(name = "NAME")
    private String name;
    @Size(max = 200)
    @Column(name = "AGENCY")
    private String agency;
    @Size(max = 200)
    @Column(name = "DRT")
    private String drt;
    @Size(max = 200)
    @Column(name = "BUSSINES_TYPE")
    private String bussinesType;
    @Size(max = 26)
    @Column(name = "SECTOR_TYPE")
    private String sectorType;
    @Size(max = 500)
    @Column(name = "LEGAL_FORM")
    private String legalForm;
    @Size(max = 128)
    @Column(name = "OWNER_STATE")
    private String ownerState;
    @Size(max = 200)
    @Column(name = "REGISTERED_DATE")
    private String registeredDate;
    @Size(max = 200)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 200)
    @Column(name = "STATUS_DATE")
    private String statusDate;
    @Size(max = 2048)
    @Column(name = "ACTIVITY")
    private String activity;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 250)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 150)
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @Size(max = 1024)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 50)
    @Column(name = "STATE")
    private String state;
    @Size(max = 50)
    @Column(name = "REGION")
    private String region;
    @Size(max = 50)
    @Column(name = "CITY")
    private String city;
    @Size(max = 50)
    @Column(name = "MUNICIPALITY")
    private String municipality;
    @Size(max = 1024)
    @Column(name = "STREET_NAME")
    private String streetName;
    @Column(name = "SKIP_RAPORT")
    private Integer skipRaport;

    
    
}
