/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bruno
 */
@Getter @Setter
public class SubjectDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    private int id;
    private String nipt;
    private String administrator;
    private String name;
    private String agency;
    private String drt;
    private String bussinesType;
    private String sectorType;
    private String legalForm;
    private String ownerState;
    private String registeredDate;
    private String status;
    private String statusDate;
    private String activity;
    private String email;
    private String phoneNo;
    private String address;
    private String state;
    private String region;
    private String city;
    private String municipality;
    private String streetName;
    
}
