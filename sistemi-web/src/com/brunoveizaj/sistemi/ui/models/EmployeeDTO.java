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
public class EmployeeDTO implements Serializable {
    
    private int id;
    private String nid;
    private String internationalNid;
    private String name;
    private String surname;
    private String dob;
    private String gender;
    private String nipt;
    private String subject;
    private String profession;
    private int salary;
    private int month;
    private int year;
    
}
