/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bruno
 */
@Getter @Setter
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nid;
    private String name;
    private String surname;
    private String fatherName;
    private String motherName;
    private String maidenName;
    private String dob;
    private String gender;
    private long familyId;
    private String familyRelation;
    private int votingNo;
    private String fraction;
    private QvDTO qv;   
    private String qvAddress;
    private String subjectPartyCode;       
    private boolean patronageStatus;
    private boolean patronageInstitutionStatus;
    private boolean poiStatus;
    private boolean memberStatus;
    private boolean firstTimeVoterStatus;
    private boolean voting2017Status;
    private boolean dapStatus;
    private boolean economicHelpStatus;
    private boolean socialHouseStatus;
    private String phone;
    private String email;
    
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nid == null) ? 0 : nid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonDTO other = (PersonDTO) obj;
		if (nid == null) {
			if (other.nid != null)
				return false;
		} else if (!nid.equals(other.nid))
			return false;
		return true;
	}
    
    
    
    
    
    
    
    
    
}
