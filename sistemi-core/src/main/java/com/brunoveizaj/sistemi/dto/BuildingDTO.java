/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Bruno
 */
@Getter @Setter
public class BuildingDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String buildingNo;
    private String buildingCode;
    private boolean hasData;
    private QvDTO qv;
    
}
