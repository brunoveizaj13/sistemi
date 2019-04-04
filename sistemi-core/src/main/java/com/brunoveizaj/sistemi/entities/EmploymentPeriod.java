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
@Table(name = "AGGR_EMPLOYEES")
@Getter @Setter
public class EmploymentPeriod implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "NID")
    private String nid;
    @Column(name = "NIPT")
    private String nipt;
    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "PROFESSION")
    private String profession;
    @Column(name = "MIN_SALARY")
    private Integer minSalary;
    @Column(name = "MAX_SALARY")
    private Integer maxSalary;
    @Column(name = "AVG_SALARY")
    private Integer avgSalary;
    @Column(name = "FROM_DATE")
    private Integer fromDate;
    @Column(name = "TO_DATE")
    private Integer toDate;

}
