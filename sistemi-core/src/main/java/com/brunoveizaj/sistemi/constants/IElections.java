package com.brunoveizaj.sistemi.constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("static-access")
public class IElections {
	
	public static Date ELECTIONS_DATE;
	
	
	public IElections()
	{
		try {
			this.ELECTIONS_DATE = new SimpleDateFormat("yyyyMMdd").parse("20190630");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	

}
