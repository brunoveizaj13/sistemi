package com.brunoveizaj.sistemi.models.map;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatronagePoint implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	String nid;
	String fullName;
	Integer patronageId;
	Integer patronageTypeId;
	String patronageType;
	
	
	String point;
	
	public void setPoint(Clob c)
	{
		if(c == null) 
		{
			point = null;
			return;
		}
		try {
			this.point = c.getSubString(1L, (int)c.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}