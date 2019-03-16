package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PHOTO", schema="SISTEMI_FULL")
@Getter @Setter
public class Photo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
	@Lob
    @Column(name = "PHOTO")
    private byte[] photo;
	@Column(name = "NID")
	private String nid;
    @Column(name = "ISSUE_DATE")
    private String issueDate;
	
	

}
