package com.brunoveizaj.sistemi.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "COUNTY_STATISTIC")
@Getter @Setter
public class RegionStatistic  implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 26)
    @Column(name = "COUNTY")
    private String region;
    @Column(name = "MUNICIPALITIES")
    private Integer municipalities;
    @Column(name = "PERSONS")
    private Integer persons;
    @Column(name = "VOTERS")
    private Integer voters;
    @Column(name = "FIRST_TIME")
    private Integer firstTime;
    @Column(name = "NO_VOTING")
    private Integer noVoting;
    @Column(name = "MEMBERS")
    private Integer members;
    @Column(name = "PATRONAGES")
    private Integer patronages;
    @Column(name = "IN_PATRONAGES")
    private Integer inPatronages;
    @Column(name = "INST_PATRONAGES")
    private Integer instPatronages;
    @Column(name = "IN_INST_PATRONAGES")
    private Integer inInstPatronages;
    @Column(name = "POIS")
    private Integer pois;
    
    
    
}