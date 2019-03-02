/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bruno
 */
@Getter @Setter
public class PoiDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private PersonDTO person;
    private boolean active;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
    private RegionDTO region;
    private MunicipalityDTO municipality;
    private PoiTypeDTO poiType;
    private QvDTO qv;
    private UnitDTO unit;

    
}
