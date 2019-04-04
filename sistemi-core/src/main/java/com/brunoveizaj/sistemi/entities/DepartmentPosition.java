package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DEPARTMENT_POSITION")
@Getter @Setter
public class DepartmentPosition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_position_seq")
    @SequenceGenerator(sequenceName = "DEPARTMENT_POSITION_SEQ", allocationSize = 1, name = "department_position_seq")
	Integer id;
	@JoinColumn(name = "PERSON_NID", referencedColumnName = "NID")
    @ManyToOne
	Person person;
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
	Department department;
	@JoinColumn(name = "FUNCTION_ID", referencedColumnName = "ID")
    @ManyToOne
	DepartmentFunction function;
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	Date startDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	Date endDate;
	@Column(name = "STATUS")
	Integer status;
	
	

}
