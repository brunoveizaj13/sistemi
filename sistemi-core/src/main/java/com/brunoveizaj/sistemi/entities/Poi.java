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
@Table(name = "POI")
@Getter @Setter
public class Poi implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "PERSON_NID", referencedColumnName = "NID")
    @ManyToOne
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
    @JoinColumn(name = "REGION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Region region;
    @JoinColumn(name = "MUNICIPALITY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Municipality municipality;
    @JoinColumn(name = "POI_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private PoiType poiType;
    @JoinColumn(name = "QV_ID", referencedColumnName = "ID")
    @ManyToOne
    private Qv qv;
    @JoinColumn(name = "UNIT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Unit unit;

   
}
