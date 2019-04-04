package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "DEPARTMENT_FUNCTION")
@Getter @Setter
public class DepartmentFunction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
	Integer id;
	@Column(name = "NAME")
	String name;
	@Column(name = "STATUS")
	Integer status;
	
	
}
