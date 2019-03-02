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
public class UnitDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private MunicipalityDTO municipality;
}
