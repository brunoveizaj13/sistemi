package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "DEPARTMENT")
@Getter @Setter
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(sequenceName = "DEPARTMENT_SEQ", allocationSize = 1, name = "department_seq")
	Integer id;
    @Column(name = "DEPARTMENT_NAME")
	String name;
    @JoinColumn(name = "PARTY_STRUCTURE_ID", referencedColumnName = "ID")
    @ManyToOne
	PartyStructure structure;
	@Column(name = "SINGLE_POSITION")
	Integer singlePosition;
	@JoinColumn(name = "PARENT_DEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
	Department parent;
	@Column(name = "AREA")
	Integer area;
	@Column(name = "REGION_ID")
	Integer regionId;
	@Column(name = "MUNICIPALITY_ID")
	Integer municipalityId;
	@Column(name = "UNIT_ID")
	Integer unitId;
	@Column(name = "QV_ID")
	Integer qvId;
	@Column(name = "FRACTION")
	String fraction;
	@Column(name = "STATUS")
	Integer status;
	@Column(name = "EXPANDED")
	Integer expanded;
	@Column(name = "COLOR")
	String color;
	
	
	
	
}
