package com.brunoveizaj.sistemi.dto;

import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;



@Getter @Setter
public class QvStatisticDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
	private Integer id;
    private String code;
    private UnitDTO unit;
    private Integer persons;
    private Integer voters;
    private Integer firstTime;
    private Integer noVoting;
    private Integer members;
    private Integer patronages;
    private Integer inPatronages;
    private Integer inInstPatronages;
    private Integer pois;
	
	
	

}
