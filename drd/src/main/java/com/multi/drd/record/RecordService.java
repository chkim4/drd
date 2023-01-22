package com.multi.drd.record;

import java.util.List;

import com.multi.drd.cardio.CardioDTO;
import com.multi.drd.fitness.FitnessDTO;

public interface RecordService {
			
	//가장 최신 기록 
	public RecordDTO findLatestRecord(int memberSEQ); 
	
	//오늘 기록 조회 
	public RecordDTO findTodayRecord(int memberSEQ); 
	
	// 한 달 기록 조회. month: 1~12
	public List<RecordDTO> findMonthlyRecord(int memberSEQ, int year, int month); 
	
	// 입력 받은 CardioList 생성
	public int createCardio(RecordDTO record);

	// 입력 받은 FitnessList 생성
	public int createFitness(RecordDTO record);

	// 입력 받은 Food 생성
	public int createFood(RecordDTO record);

	// 입력 받은 CardioList 업데이트
	public int updateCardio(RecordDTO record);

	// 입력 받은 FitnessList 업데이트
	public int updateFitness(RecordDTO record);

	// 입력 받은 FoodList 업데이트
	public int updateFood(RecordDTO record);

	// 입력 받은 status 업데이트
	public int updateStatus(RecordDTO record);

	// 입력 받은 field 삭제
	public int deleteField(RecordDTO record, String field);
	
	// 모든 유산소 운동 조회
	public List<CardioDTO> findAllCardio(); 
	
	public List<FitnessDTO> findAllFitness();
}
