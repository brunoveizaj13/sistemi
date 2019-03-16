/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "USERS")
@Getter @Setter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull   
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 20)
    @Column(name = "SECRET")
    private String secret;
    @JoinColumn(name = "ROLE_CODE", referencedColumnName = "CODE")
    @ManyToOne
    private Role role;
    @JoinColumn(name = "INSTITUTION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Institution institution;
    @Column(name = "STATUS")
    private Integer status;
    @Size(max = 20)
    @Column(name = "NAME")
    private String name;
    @Size(max = 20)
    @Column(name = "SURNAME")
    private String surname;
    @Size(max = 30)
    @Column(name = "MUNICIPALITY")
    private String municipality;
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "CREATE_USER_ID")
    private Integer createUserId;
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Column(name = "MODIFY_USER_ID")
    private Integer modifyUserId;

    
    
}
