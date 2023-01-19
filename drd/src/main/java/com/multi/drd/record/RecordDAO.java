package com.multi.drd.record;

import java.util.Date;
import java.util.List;

public interface RecordDAO { 
 
	public RecordDTO findLatestRecord (int memberSEQ); 
	
	public RecordDTO findTodayRecord(int memberSEQ); 
	
	public List<RecordDTO> findMonthlyRecord(int memberSEQ, int year, int month);
	
	public int updateCardio(RecordDTO record); 
	
	public RecordDTO findDailyRecord(int memberSEQ, Date date);
}
