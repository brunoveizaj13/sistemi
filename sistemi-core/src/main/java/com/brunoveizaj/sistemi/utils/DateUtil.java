package com.brunoveizaj.sistemi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.brunoveizaj.sistemi.constants.IDate;



public class DateUtil {

	
	public static String formatDateToString(Date date)
	{
		if(date == null) return null;
		
		DateFormat df = new SimpleDateFormat(IDate.DATE_FORMAT);
		
		return df.format(date);
	}
	
	public static Date formatStringToDate(String date)
	{
		try {
			
		if(date == null) return null;
		
		DateFormat df = new SimpleDateFormat(IDate.DATE_FORMAT);
		
		return df.parse(date);
		
		}catch(Exception e) {
			return null;
		}
	}
	
	public static String formatTimestampToString(Date date)
	{
		if(date == null) return null;
		
		DateFormat df = new SimpleDateFormat(IDate.TIMESTAMP_FORMAT);
		
		return df.format(date);
	}
	
	public static Date formatStringToTimestamp(String date)
	{
		try {
			
		if(date == null) return null;
		
		DateFormat df = new SimpleDateFormat(IDate.TIMESTAMP_FORMAT);
		
		return df.parse(date);
		
		}catch(Exception e) {
			return null;
		}
	}
	
	
	public static String formatReverseToNormalDate(String reverseDate)
	{
		if(reverseDate != null && reverseDate.trim().length() == 8)
		{
			return reverseDate.substring(6, 8)+"."+reverseDate.substring(4, 6)+"."+reverseDate.substring(0, 4);
		}
		
		return null;
	}
	
	public static String formatNormalToReverseDate(String date)
	{
		if(date != null && date.trim().length() == 10)
		{
			return date.substring(6, 10) + date.substring(3, 5) + date.substring(0, 2);
		}
		
		return null;
	}
	
	
	public static String getDobReverse(Integer age)
	{
		if(age != null)
		{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -age);
			
			DateFormat df = new SimpleDateFormat(IDate.REVERSE_DATE_FORMAT);
			return df.format(cal.getTime());
			
		}
		
		return null;
	}
	
	
	public static Date addDaysToDate(Date date,int days)
	   {       
	       if (date == null) {
	           return null;
	       }
	       Calendar calendar = Calendar.getInstance();
	       calendar.setTime(date);
	       calendar.add(Calendar.DATE, days);

	       return calendar.getTime();
	   }
	
	
}
