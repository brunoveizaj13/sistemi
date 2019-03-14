package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MapEntity implements Serializable {
	
	String id;
	String name;
	Integer type;
	
	public MapEntity(PersonDTO p)
	{
		this.id = p.getNid();
		this.name = p.getName() + " " + p.getFatherName() + " " + p.getSurname()+", QV: "+ p.getQv().getCode() + "/" + p.getFraction();
		this.type = 1;
	}
	
	public MapEntity(QvDTO qv)
	{
		this.id = String.valueOf(qv.getId());
		this.name = "QV:" + qv.getCode() + " - " + qv.getUnit().getName();
		this.type = 2;
	}
	
	public MapEntity(UnitDTO u)
	{
		this.id = String.valueOf(u.getId());
		this.name = u.getName();
		this.type = 3;
	}

	public MapEntity(BuildingDTO b)
	{
		this.id = String.valueOf(b.getId());
		this.name = b.getBuildingNo()+"-"+b.getBuildingCode();
		this.type = 4;
	}
	
	public MapEntity() {
	}
	
	public MapEntity(String id, int type) {
		this.id = id;
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MapEntity other = (MapEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
}
