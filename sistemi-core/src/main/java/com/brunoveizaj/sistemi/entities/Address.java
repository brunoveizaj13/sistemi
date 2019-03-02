/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bruno
 */
@Entity
@Table(name = "V_BRUNO_HARTAT")
@Getter @Setter
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 50)
    @Column(name = "ID")
    private String id;
    @Column(name = "ADDRESS_ID")
    private BigInteger addressId;
    @Size(max = 20)
    @Column(name = "SUBJECT_TYPE")
    private String subjectType;
    @Size(max = 10)
    @Column(name = "NID")
    private String nid;
    @Size(max = 242)
    @Column(name = "NAME")
    private String name;
    @Size(max = 56)
    @Column(name = "STREET")
    private String street;
    @Size(max = 52)
    @Column(name = "BUILDING_ENTRANCE")
    private String buildingEntrance;
    @Size(max = 124)
    @Column(name = "ADM_UNIT")
    private String admUnit;
    @Size(max = 30)
    @Column(name = "CITY")
    private String city;
    @Column(name = "ENTRANCE_ID")
    private BigInteger entranceId;
    @Size(max = 60)
    @Column(name = "ENTRANCE_NO")
    private String entranceNo;
    @Column(name = "BUILDING_ID")
    private BigInteger buildingId;
    @Column(name = "BUILDING_NO")
    private BigInteger buildingNo;
    @Size(max = 50)
    @Column(name = "BUILDING_CODE")
    private String buildingCode;
    @Size(max = 30)
    @Column(name = "MUNICIPALITY")
    private String municipality;
    
}
