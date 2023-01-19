package com.multi.drd.record;

import java.util.List;

public interface RecordService {
			
	//가장 최신 기록 
	public RecordDTO findLatestRecord(int memberSEQ); 
	
	//오늘 기록 조회 
	public RecordDTO findTodayRecord(int memberSEQ); 
	
	// 한 달 기록 조회. month: 1~12
	public List<RecordDTO> findMonthlyRecord(int memberSEQ, int year, int month); 
	
	// 입력 받은 CardioList 업데이트
	public int updateCardio(RecordDTO record);

	// 입력 받은 FitnessList 업데이트
	public int updateFitness(RecordDTO record);

	// 입력 받은 FoodList 업데이트
	public int updateFood(RecordDTO record);
}
