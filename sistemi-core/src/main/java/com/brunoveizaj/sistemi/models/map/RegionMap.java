package com.brunoveizaj.sistemi.models.map;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegionMap implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Integer regionId;
	String name;
	String center;
	String shape;
	
	
	public void setCenter(Clob c)
	{
		if(c == null) center = null;
		try {
			this.center = c.getSubString(1L, (int)c.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setShape(Clob c)
	{
		if(c == null) shape = null;
		try {
			this.shape = c.getSubString(1L, (int)c.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}