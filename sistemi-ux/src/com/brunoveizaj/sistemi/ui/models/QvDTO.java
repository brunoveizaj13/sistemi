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
public class QvDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    

    private int id;

    private String code;
    private UnitDTO unit;
    
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		QvDTO other = (QvDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
    
    
    
    
   
}
