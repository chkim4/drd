package com.multi.drd.record;

import java.util.Date;
import java.util.List;

import com.multi.drd.cardio.CardioDTO;
import com.multi.drd.fitness.FitnessDTO;
import com.multi.drd.goal.GoalDTO;
import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;

public interface RecordDAO { 
 
	public RecordDTO findLatestRecord (int memberSEQ); 
	
	public RecordDTO findTodayRecord(int memberSEQ);  
	
	public List<RecordDTO> findWeeklyRecord(int memberSEQ);
	
	public List<RecordDTO> findMonthlyRecord(int memberSEQ, int year, int month);
	
	public RecordDTO findDailyRecord(int memberSEQ, Date date); 
	
	public int createOne(RecordDTO record);
	
	public int updateCardio(RecordDTO record); 

	public int updateFitness(RecordDTO record);  
	
	public int updateFood(RecordDTO record); 
	
	public int updateStatus(RecordDTO record);
	
	public int deleteDailyRecord(RecordDTO record);
	
	public int deleteField(RecordDTO record, String field); 
	
	public List<CardioDTO> findAllCardio(); 
	
	public List<FitnessDTO> findAllFitness(); 
	
	// 일주일 내 기록 중 totalExerciseTime > 0 인 기록만 조회. goalCalory, goalProtein 업데이트 용
	public List<RecordDTO> findWeeklyExerciseRecord(int memberSEQ);
	
	// 기록 생성/갱신/삭제 시 그 내용을 기반으로 activityLevel 변경 
	public int updateMemberBioByRecord(MemberBioDTO memberBio);
	
	// 기록 생성/갱신/삭제 시 그 내용을 기반으로 일일 권장 섭취 칼로리와 주간 권장 섭취 단백질 변경
	public int updateGoalByRecord(GoalDTO goal);
	

}
