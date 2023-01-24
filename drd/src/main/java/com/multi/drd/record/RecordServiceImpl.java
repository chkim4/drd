package com.multi.drd.record;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.drd.cardio.CardioDTO;
import com.multi.drd.fitness.FitnessDTO;
import com.multi.drd.food.FoodDAO;
import com.multi.drd.food.FoodDTO;
import com.multi.drd.goal.GoalDTO;
import com.multi.drd.json.FoodObj;
import com.multi.drd.member.MemberDAO;
import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDAO;
import com.multi.drd.memberbio.MemberBioDTO;

@Service
public class RecordServiceImpl implements RecordService {
	RecordDAO dao; 
	FoodDAO foodDao; 
	MemberDAO memberDAO;
	MemberBioDAO memberBioDAO;
	
	@Autowired
	public RecordServiceImpl(RecordDAO dao, FoodDAO foodDao, MemberDAO memberDAO, MemberBioDAO memberBioDAO) {
		super();
		this.dao = dao;
		this.foodDao = foodDao;
		this.memberDAO = memberDAO;
		this.memberBioDAO = memberBioDAO;
	}

	//-----------------------------------------조회 관련-----------------------------------------
	@Override
	public RecordDTO findLatestRecord(int memberSEQ) {
		return dao.findLatestRecord(memberSEQ);
	}
	
	@Override
	public RecordDTO findTodayRecord(int memberSEQ) {
		return dao.findTodayRecord(memberSEQ);
	} 
	
	@Override
	public List<RecordDTO> findWeeklyRecord(int memberSEQ) {
		return dao.findWeeklyRecord(memberSEQ);
	}	

	@Override
	public List<RecordDTO> findMonthlyRecord(int memberSEQ, int year, int month) {
		return dao.findMonthlyRecord(memberSEQ, year, month);
	} 
	
	//-----------------------------------------유산소(cardio) 관련-----------------------------------------
	@Override
	public List<CardioDTO> findAllCardio() {
		return dao.findAllCardio();
	}
	
	@Override
	public int createCardio(RecordDTO record, MemberDTO member) {
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		int result = 0;
		boolean isOriginalRecordNotNull = originalRecord != null;
		
		// 기존 기록이 있다면 그에 맞추어 업데이트 
	    // (기존 기록이 있는 상태에서 insert 시 다른 document에 삽입됨 + totalExerciseTime 계산이 안 됨.)
		if(isOriginalRecordNotNull) {
		
			//만약 무산소 운동 기록이 있다면 totalExerciseTime = (original의 무산소 운동 시간) + (record의 유산소 운동 시간)
			if(originalRecord.getFitnessObj() != null) {
				record.setTotalExerciseTime(originalRecord.getFitnessObj().getTotalTime()+record.getCardioObj().getTotalTime());
			} 
			else {
				// 만약 무산소 운동 기록이 없다면 totalExerciseTime을 유산소 운동 시간의 총합으로 설정.
				record.setTotalExerciseTime(record.getCardioObj().getTotalTime());
			}
			result = dao.updateCardio(record); 
		}
		else {
			result = dao.createOne(record);
		}
		
		updateMemberDataByRecord(member);
		return result;
	}

	@Override
	public int updateCardio(RecordDTO record, MemberDTO member) {
		
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		int result = 0;
		
		// 총 운동 시간: (원래 총 운동 시간 - 원래 유산소 운동 시간) + 업데이트 된 유산소 운동 시간 
		int exerciseTotalTime = originalRecord.getTotalExerciseTime() 
									- originalRecord.getCardioObj().getTotalTime() + record.getCardioObj().getTotalTime(); 
		
		record.setTotalExerciseTime(exerciseTotalTime); 
		
		// 사용자가 모달창에서 모든 목록을 삭제했을 경우 필드 삭제
		if(record.getCardioObj().getTotalTime() == 0) {
			result = dao.deleteField(record, "cardioObj");
		}
		else {
			result = dao.updateCardio(record);
		} 
		
		updateMemberDataByRecord(member);
		return result; 
	}
	//-----------------------------------------무산소(fitness) 관련-----------------------------------------
	
	@Override
	public List<FitnessDTO> findAllFitness() {
		return dao.findAllFitness();
	} 
	
	@Override
	public int createFitness(RecordDTO record, MemberDTO member) {
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		int result = 0;
		boolean isOriginalRecordNotNull = originalRecord != null;
		
		// 기존 기록이 있다면 그에 맞추어 업데이트 
	    // (기존 기록이 있는 상태에서 insert 시 다른 document에 삽입됨 + totalExerciseTime 계산이 안 됨.)
		if(isOriginalRecordNotNull) {
			
			//만약 유산소 운동 기록이 있다면 totalExerciseTime = (original의 유산소 운동 시간) + (record의 무산소 운동 시간)
			if(originalRecord.getCardioObj() != null) {
				record.setTotalExerciseTime(originalRecord.getCardioObj().getTotalTime()+record.getFitnessObj().getTotalTime());
			} 
			else {
				// 만약 유산소 운동 기록이 없다면 totalExerciseTime을 유산소 운동 시간의 총합으로 설정.
				record.setTotalExerciseTime(record.getFitnessObj().getTotalTime());
			}
			
			result = dao.updateFitness(record); 
		}
		else {
			result = dao.createOne(record);
		} 
		updateMemberDataByRecord(member);
		return result;
	}
	
	@Override
	public int updateFitness(RecordDTO record, MemberDTO member) {
		
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		int result = 0; 
		
		// 총 운동 시간: (원래 총 운동 시간 - 원래 무산소 운동 시간) + 업데이트 된 무산소 운동 시간 
		int exerciseTotalTime = originalRecord.getTotalExerciseTime() 
				- originalRecord.getFitnessObj().getTotalTime() + record.getFitnessObj().getTotalTime(); 
		
		record.setTotalExerciseTime(exerciseTotalTime); 
		
		// 사용자가 모달창에서 모든 목록을 삭제했을 경우 필드 삭제
		if(record.getFitnessObj().getTotalTime() == 0) {
			result = dao.deleteField(record, "fitnessObj");
		}
		else {
			result = dao.updateFitness(record);
		} 
		
		
		updateMemberDataByRecord(member);
		return result;
	}
	
	//-----------------------------------------식단(food) 관련-----------------------------------------
	
	@Override
	public int createFood(RecordDTO record) {
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		
		int result = 0; 
		RecordDTO calcRecord = calcFoodInfo(record);
		
		// 기록이 없다면 새 기록 생성
		if(originalRecord == null) {
			result = dao.createOne(calcRecord);
		}
		else {
			result = dao.updateFood(calcRecord);
		}
		
		return result;
	}
	
	@Override
	public int updateFood(RecordDTO record) {	
		return dao.updateFood(calcFoodInfo(record));
	}
	
	// 실제 섭취량과 1회 섭취량을 비교하여 탄/단/지/콜레스트롤 및 totalCalory 계산 
	public RecordDTO calcFoodInfo(RecordDTO record){
		// Record 객체에서 FoodObj 발췌 및 Food 테이블 참조를 위해 SEQ만 모은 리스트(foodSEQList) 생성
				List<FoodObj> recordFoodObjList = record.getFoodObj(); 
				List<String> foodSEQList = new ArrayList<String>(); 
				
				// 기록에 저장된 음식 정보 가져와서 foods에 저장
				for(FoodObj food : recordFoodObjList) {
					foodSEQList.add(Integer.toString(food.getFoodSEQ()));  
				} 
				List<FoodDTO> foods = foodDao.findFoodListByPK(foodSEQList);
				
				double ratio = 1.0; 
				int totalCalory = 0;
				int calory = 0;
				int carb= 0;
				int protein = 0;
				int fat = 0;
				int cholesterol = 0;
			 
				record.setTotalCalory(0);
				
				for(int i=0; i<recordFoodObjList.size(); i++) {
					FoodDTO food = foods.get(i);
					
					// (섭취량)/(1회 제공량) 구하기 
					ratio =  Double.parseDouble(String.format("%.2f", (recordFoodObjList.get(i).getQuantity()*1.0)/(food.getQuantity())));
					
					// totalCalory, 각 음식의 칼로리 두 곳에 반영해야 하므로 각 음식의 칼로리만 별도 선언. 
					calory = (int) Math.round(food.getCalory()*ratio);
					
					// record 의 totalCalory에 누적
					totalCalory+=calory;  
					
					carb = (int) Math.round(food.getCarb()*ratio);
					protein = (int) Math.round(food.getProtein()*ratio);
					fat = (int) Math.round(food.getFat()*ratio);
					cholesterol = (int) Math.round(food.getCholesterol()*ratio);
					
					// recordFoodObjList에 반영
					recordFoodObjList.get(i).setCalory(calory);
					recordFoodObjList.get(i).setCarb(carb);
					recordFoodObjList.get(i).setProtein(protein);
					recordFoodObjList.get(i).setFat(fat);
					recordFoodObjList.get(i).setCholesterol(cholesterol);	
				} 
				
				// 계산이 완료된 totalCalory 및 각 음식 기록을 저장.
				record.setTotalCalory(totalCalory);
				record.setFoodObj(recordFoodObjList);
				
				return record;
	}
	
	//-----------------------------------------상태 점수(status) 관련-----------------------------------------
	@Override
	public int createStatus(RecordDTO record) {
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		
		int result = 0; 
		
		// 기록이 없다면 새 기록 생성
		if(originalRecord == null) {
			result = dao.createOne(record);
		}
		else {
			result = dao.updateStatus(record);
		}
		
		return result;
	}
	
	@Override
	public int updateStatus(RecordDTO record) {
		return dao.updateStatus(record);
	}

	//-----------------------------------------기타----------------------------------------- 
	
	@Override
	public int deleteDailyRecord(RecordDTO record, MemberDTO member) {
		int result = dao.deleteDailyRecord(record);
		
		updateMemberDataByRecord(member);
		return result;
	}
	@Override
	public int deleteField(RecordDTO record, String field, MemberDTO member) {
		
		Boolean isExerciseDelete = false;
		int result = 0;
		
		
		if(field.equals("cardioObj") || field.equals("fitnessObj")) {
			isExerciseDelete = true;
			RecordDTO originalRecord = dao.findDailyRecord(record.getMemberSEQ(), record.getDate());		
			
			// 총 운동 시간: 유산소 삭제 시: (원래 전체 운동 시간-원래 유산소 시간) / 무산소 삭제 시: (원래 전체 운동 시간-원래 무산소 시간) 
			if(field.equals("cardioObj")) {
				record.setTotalExerciseTime(originalRecord.getTotalExerciseTime() - originalRecord.getCardioObj().getTotalTime());
			}
			else {
				record.setTotalExerciseTime(originalRecord.getTotalExerciseTime() - originalRecord.getFitnessObj().getTotalTime());
			}
		}// if cardioObj || fitnessObj	 
		
		
		result = dao.deleteField(record, field); 
		
		if(isExerciseDelete) {
			updateMemberDataByRecord(member);
		}
		
			
		return result;
	}

	// 설명은 RecordService 참고
	@Override
	public void updateMemberDataByRecord(MemberDTO member) {
		
		/* size: weeklyRecord의 길이. 많이 써서 임시 변수에 저장.
		 * sum, avg: 일 별 총 운동 시간의 합계와 평균 저장. (합계: 평균을 구하기 위한 임시 변수)
		 */
		int memberSEQ = member.getMemberSEQ();
		// 기록 집계에 필요한 변수들 
		List<RecordDTO> weeklyRecord = dao.findWeeklyExerciseRecord(memberSEQ); 
		int size = weeklyRecord.size();
		int sum = 0; 
		double avg = 0.0;  
		
		// dao와의 통신에 필요한 객체들 (memberDTO는 파라미터로 받으므로 제외)
		MemberBioDTO memberBio = memberBioDAO.findByPK(memberSEQ);
		GoalDTO goal = new GoalDTO(); 
		
		goal.setMemberSEQ(memberSEQ);
		
		// 목표 설정에 필요한 변수들 
		double goalProteinRatio = 0.8; // 운동 횟수 및 운동 시간에 따른 권장 섭취 칼로리의 비율 조정.
		double activityLevel = 1.0; 
		int goalCalory = 0; 
		int goalProtein = 0;
		double rawGoalCalory = 0.0; 
		
		//목표 설정을 위한 신체 정보를 임시 저장하는 변수들 
		int gender = member.getGender();
		int age = member.getAge();
		double height = memberBio.getHeight()/100; //m 로 전환
		double weight = memberBio.getWeight();
		
		// 1주일 기록 중 totalExerciseTime 만 추출
		List<Integer> totalExerciseTimeList = new ArrayList<Integer>();
		
		for(RecordDTO record : weeklyRecord) {
			totalExerciseTimeList.add(record.getTotalExerciseTime());
			sum+=record.getTotalExerciseTime();
		}
		
		avg = Double.parseDouble(String.format("%.1f", (sum*1.0)/size));
		
		// 2015 한국인영양소 섭취기준 활용 기준 신체 활동 지수 설정
		switch(size) {	
			case 0:
				activityLevel = 1.0; // 일주일 0회 운동: PA: 1.0 으로 설정.  
				break;
			
			case 1:
			case 2:
				activityLevel = 1.11; // 일주일 1~2회 운동: PA: 1.11로 설정 
				break;			
			
			case 3:
			case 4:
			case 5:
				activityLevel = 1.25; // 일주일 3~5회 운동: PA: 1.25 으로 설정 
				break;
			
			case 6:
			case 7:
				activityLevel = 1.48; // 일주일 6회 이상 운동: PA: 1.48로 설정 
				break; 
				
			default: 
				activityLevel = 1.0;
				break;
		}   
		
		// 여성일 경우
		if(gender == 0) { 
			rawGoalCalory = 354-6.91 * age + activityLevel * (9.36 * weight + 726 * height); 
		}  
		// 남성일 경우 
		else {		
			rawGoalCalory = 662-9.53 * age + activityLevel * (15.91 * weight + 539.6 * height);
		}
		
		goalCalory = Integer.parseInt(String.format("%.0f", rawGoalCalory));
		
		if(size >= 5 && avg >= 60) {
			goalProteinRatio = 1.2;
		}
		else if(size >= 3 && avg >= 45) {
			goalProteinRatio = 1.0;
		}
		else {
			goalProteinRatio = 0.8;
		}
		
		goalProtein = (int) Math.round(goalProteinRatio*memberBio.getWeight()*7);
		
		// 가공한 필드를 DTO에 반영 및 업데이트 (memberBio의 activityLevel, Goal의 goalCalory, goalProtein)
		memberBio.setActivityLevel(activityLevel);
		goal.setGoalCalory(goalCalory);
		goal.setGoalProtein(goalProtein);
		
		// DAO 호출
		dao.updateMemberBioByRecord(memberBio);
		dao.updateGoalByRecord(goal);
	}	
}
