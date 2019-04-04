/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bruno
 */
@Getter @Setter
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private BigInteger addressId;
    private String subjectType;
    private String nid;
    private String name;
    private String street;
    private String buildingEntrance;
    private String admUnit;
    private String city;
    private BigInteger entranceId;
    private String entranceNo;
    private BigInteger buildingId;
    private BigInteger buildingNo;
    private String buildingCode;
    private String municipality;
    
}
