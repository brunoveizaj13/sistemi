package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;

public class Param implements Serializable{


	private static final long serialVersionUID = 1L;
	
	
	String name;
	String value;
	
	
	public Param() {}
	
	public Param(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
