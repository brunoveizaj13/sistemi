/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
@Table(name = "PATRONAGE")
@Getter @Setter
public class Patronage implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patronage_seq")
    @SequenceGenerator(sequenceName = "PATRONAGE_ID_SEQ", allocationSize = 1, name = "patronage_seq")
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "PERSON_NID", referencedColumnName = "NID")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Person person;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Size(max = 20)
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Size(max = 20)
    @Column(name = "UPDATE_USER")
    private String updateUser;
    @JoinColumn(name = "INSTITUTION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Institution institution;
    @JoinColumn(name = "PATRONAGE_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private PatronageType patronageType;

    
    
}
