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
@Table(name = "BUILDING")
@Getter @Setter
public class Building implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 45)
    @Column(name = "BUILDING_NO")
    private String buildingNo;
    @Size(max = 45)
    @Column(name = "BUILDING_CODE")
    private String buildingCode;
    @Column(name = "HAS_DATA")
    private Integer hasData;
    @JoinColumn(name = "QV_ID", referencedColumnName = "ID")
    @ManyToOne
    private Qv qv;
    
}
