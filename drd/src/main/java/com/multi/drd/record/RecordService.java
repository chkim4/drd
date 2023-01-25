package com.multi.drd.record;

import java.util.List;

import com.multi.drd.cardio.CardioDTO;
import com.multi.drd.fitness.FitnessDTO;
import com.multi.drd.member.MemberDTO;

public interface RecordService {
			
	//가장 최신 기록 
	public RecordDTO findLatestRecord(int memberSEQ); 
	
	//오늘 기록 조회 
	public RecordDTO findTodayRecord(int memberSEQ); 

	//7일 전 ~ 오늘 기록 조회 
	public List<RecordDTO> findWeeklyRecord(int memberSEQ); 
	
	// 한 달 기록 조회. month: 1~12
	public List<RecordDTO> findMonthlyRecord(int memberSEQ, int year, int month); 
	
	// 입력 받은 CardioList 생성
	public int createCardio(RecordDTO record, MemberDTO member);

	// 입력 받은 FitnessList 생성
	public int createFitness(RecordDTO record, MemberDTO member);

	// 입력 받은 Food 생성
	public int createFood(RecordDTO record);

	// 입력 받은 status 생성
	public int createStatus(RecordDTO record);

	// 입력 받은 CardioList 업데이트
	public int updateCardio(RecordDTO record, MemberDTO member);

	// 입력 받은 FitnessList 업데이트
	public int updateFitness(RecordDTO record, MemberDTO member);

	// 입력 받은 FoodList 업데이트
	public int updateFood(RecordDTO record);

	// 입력 받은 status 업데이트
	public int updateStatus(RecordDTO record);

	// 입력 받은 날짜의 기록 삭제. updateMemberDataByRecord 실행을 위해 member 추가
	public int deleteDailyRecord(RecordDTO record, MemberDTO member);

	// 입력 받은 field 삭제. updateMemberDataByRecord 실행을 위해 member 추가 
	public int deleteField(RecordDTO record, String field, MemberDTO member);
	
	// 모든 유산소 운동 조회
	public List<CardioDTO> findAllCardio(); 
	
	public List<FitnessDTO> findAllFitness(); 
	
	/*
	 * 기록 생성/갱신/삭제 시 그 내용을 기반으로 일일 권장 섭취 칼로리와 주간 권장 섭취 단백질 변경 
	 * [칼로리] 
	 * 보건복지부 자료 활용 (명확한 기준 없음. 회원 가입 시와 동일한 기준 적용)
	 * 일일 권장 칼로리 
	 * 키: m, 몸무게: kg
	 * - 여성: 354-6.91 * age + activityLevel * (9.36 * weight + 726 * height); 
	 * - 남성:  662-9.53 * age + activityLevel * (15.91 * weight + 539.6 * height) 
	 * - 활동 지수(activityLevel) 
	 * 1) 1주일 0회 운동: 1.0 
	 * 2) 1주일 1~2회 운동: 1.11
	 * 3) 1주일 3~5회 운동: 1.25 
	 * 4) 1주일 6회 이상 운동: 1.48
	 * 
	 * [단백질]
	 * 마크 타노폴스키의 연구 결과
	 * 일일 권장 단백질 섭취량 
	 * 1) 운동 횟수 주 2회 이하: 체중 1kg 당 단백질 0.8g 
	 * 2) 운동 횟수 주 3-5회 운동, 평균 45분~1시간 운동: 체중 1kg당 단백질 1.0g 
	 * 3) 운동 횟수 주 5회이상, 평균 1시간 이상 운동: 체중 1kg당 단백질 1.2g 
	 *  
	 * 
	 */
	public void updateMemberDataByRecord(MemberDTO member);
	
}
