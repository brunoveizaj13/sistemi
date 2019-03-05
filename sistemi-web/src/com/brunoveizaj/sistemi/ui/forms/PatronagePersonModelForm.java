package com.brunoveizaj.sistemi.ui.forms;

import java.io.Serializable;

import com.brunoveizaj.sistemi.ui.models.PersonDTO;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PatronagePersonModelForm implements Serializable {
	
	

	private static final long serialVersionUID = 1L;

	PersonDTO person;
	String phone;
	String email;
	boolean withFamily;
	Integer buildingId;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		PatronagePersonModelForm other = (PatronagePersonModelForm) obj;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}
	
	
	
	
	
}
