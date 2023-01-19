package com.multi.drd.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtils {    

	
	private final static String DB_FORMAT_DAY = "yyyy-M-d";    
	private final static String DB_FORMAT_DATE = "yyyy-M-d HH:mm:ss";    
	private final static DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern(DB_FORMAT_DATE); 
     
    private DateUtils() {
        // not publicly instantiable
    }       
    
    /* '2023-01-01' 형태의 날짜를 MongoDB에서 날짜 검색이 가능하도록 형태 변환. 
     * moment 
     *  - "start" 일 경우 날짜(dateStr) 뒤에 00:00:00를 concat
     *  - "end" 일 경우 날짜(dateStr) 뒤에 23:59:59를 concat
     *  
     */
    public static Date getISODate(String dateStr, String moment) {
        
    	if(moment.equals("start")) {
    		dateStr += " 00:00:00";
    	}
    	else {
    		dateStr += " 23:59:59";
    	}
    	
    	final DateFormat formatter = new SimpleDateFormat(DB_FORMAT_DATE);
    	       
        try {
            return formatter.parse(dateStr);
        } 
        catch (ParseException e) {                
            System.err.println(e);
        	return null;
        }
    }   
    
    public static Date[] getDailyISODate(Date date){
    	final DateFormat formatter = new SimpleDateFormat(DB_FORMAT_DAY); 
    	
    	String dateStr = formatter.format(date);
    	String stringStartDate = dateStr + " 00:00:00";
    	String stringEndDate = dateStr + " 23:59:59"; 
    	
    	
    	
    	Date startDate;
    	Date endDate;
		try {
			startDate = new SimpleDateFormat(DB_FORMAT_DATE).parse(stringStartDate); 
			endDate = new SimpleDateFormat(DB_FORMAT_DATE).parse(stringEndDate);
		} 
		catch (ParseException e) {	
			e.printStackTrace(); 
			return null;
		}
    	
    	Date[] dates = {startDate, endDate};
    	
    	return dates;	
    }  
    
    /* 
     * input 값으로 들어온 날짜에 해당하는 월의 시작일과 마지막 일을 ISO 형태의 date로 리턴 
     * 시간될 때 LocalDate로 해보자. Mongo에서 LocalDate 적용하려면 설정할 게 많아보이지만, 
     * Date 객체는 Deprecated 되었기에 LocalDate로 작성하는 게 맞다. 
     * 일단 LocalDate로 전환하기 편하게 코딩함.
     */
    public static Date[] getMonthlyISODate(int year, int month) {
    	
	    	LocalDate rawStartDate = LocalDate.of(year,month,1);  
	    	LocalDate rawEndDate = rawStartDate.plusDays(rawStartDate.lengthOfMonth()-1);    
	    	
    	    LocalDateTime localStartDate = LocalDateTime.of(rawStartDate, LocalTime.MIDNIGHT);    	
    	    LocalDateTime localEndDate = LocalDateTime.of(rawEndDate, LocalTime.MAX); 
    	    
    	    String stringStartDate = localStartDate.format(isoFormatter);
    	    String stringEndDate = localEndDate.format(isoFormatter);
    	    
    	    Date startDate;
    	    Date endDate;
			
    	    try {
				startDate = new SimpleDateFormat(DB_FORMAT_DATE).parse(stringStartDate); 
				endDate = new SimpleDateFormat(DB_FORMAT_DATE).parse(stringEndDate);
			} catch (ParseException e) {
				e.printStackTrace(); 
				return null;
			}
    	    Date[] dates = {startDate, endDate};
	    	
	    	return dates;	
	    }    
    
    /*
     * 현재 날짜의 첫 일과 마지막 일을 ISODATE 형식으로 리턴.
     */
    public static Date[] getCurrentMonthlyISODate() { 
    	Date date = new Date();
    	LocalDate localDate = date.toInstant().atZone(ZoneId.of("Asia/Seoul")).toLocalDate(); 
    	Date[] dates = null;
    	
    	dates = getMonthlyISODate(localDate.getYear(), localDate.getMonthValue()); // getMonthValue: 1~12 
    	return dates;
    } 
    
    public static Date convertTimestampToDate(String timestamp) {
    	Timestamp stamp = new Timestamp(System.currentTimeMillis());
    	Date date = new Date(stamp.getTime());
    	
    	return date;
    }
} 




