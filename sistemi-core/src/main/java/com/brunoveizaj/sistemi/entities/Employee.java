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
@Table(name = "EMPLOYEE")
@Getter @Setter
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "NID")
    private String nid;
    @Size(max = 30)
    @Column(name = "INTERNATIONAL_NID")
    private String internationalNid;
    @Size(max = 500)
    @Column(name = "NAME")
    private String name;
    @Size(max = 500)
    @Column(name = "SURNAME")
    private String surname;
    @Size(max = 26)
    @Column(name = "DOB")
    private String dob;
    @Size(max = 26)
    @Column(name = "GENDER")
    private String gender;
    @Size(max = 26)
    @Column(name = "NIPT")
    private String nipt;
    @Size(max = 1024)
    @Column(name = "SUBJECT")
    private String subject;
    @Size(max = 1024)
    @Column(name = "PROFESSION")
    private String profession;
    @Column(name = "SALARY")
    private Integer salary;
    @Column(name = "TOTAL_CONTRIBUTES")
    private Integer totalContributes;
    @Column(name = "TAP")
    private Integer tap;
    @Column(name = "MONTH")
    private Integer month;
    @Column(name = "YEAR")
    private Integer year;

    
    
}
