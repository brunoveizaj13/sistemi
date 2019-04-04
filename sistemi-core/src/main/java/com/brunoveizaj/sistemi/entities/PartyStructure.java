package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PARTY_STRUCTURE")
@Getter @Setter
public class PartyStructure implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
	Integer id;
	@Column(name = "NAME")
	String name;
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	Date startDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	Date endDate;
	@Column(name = "TYPE")
	String type;
	@Column(name = "ACTIVE")
	Integer active;
	

}
