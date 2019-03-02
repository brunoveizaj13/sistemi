/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ENTRANCE")
@Getter @Setter
public class Entrance implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 60)
    @Column(name = "ENTRANCE_NO")
    private String entranceNo;
    @Size(max = 40)
    @Column(name = "NIVELI")
    private String niveli;
    @Column(name = "FLOOR_NO")
    private BigInteger floorNo;
    @JoinColumn(name = "BUILDING_ID", referencedColumnName = "ID")
    @ManyToOne
    private Building building;
    @Column(name = "SHAPE_CENTER")
    private String shapeCenter;

   
}
