package com.brunoveizaj.sistemi.models.map;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BuildingMap implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Integer buildingId;
	String buildingNo;
	String buildingCode;
	Integer hasData;
	String center;
	String shape;
	
	public void setCenter(Clob c)
	{
		if(c == null) 
		{
			center = null;
			return;
		}
		try {
			this.center = c.getSubString(1L, (int)c.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setShape(Clob c)
	{
		if(c == null) 
		{
			shape = null;
			return;
		}
		try {
			this.shape = c.getSubString(1L, (int)c.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
