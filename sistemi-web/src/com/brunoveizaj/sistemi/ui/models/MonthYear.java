package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MonthYear implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	Integer month;
	Integer year;
	
	
	public MonthYear() {}
	
	
	public MonthYear(Integer month, Integer year) {
		super();
		this.month = month;
		this.year = year;
	}
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		MonthYear other = (MonthYear) obj;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
	
	
	
	
}
