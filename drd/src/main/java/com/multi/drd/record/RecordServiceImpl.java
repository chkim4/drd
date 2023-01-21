package com.multi.drd.record;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.drd.food.FoodDAO;
import com.multi.drd.food.FoodDTO;
import com.multi.drd.json.FoodObj;

@Service
public class RecordServiceImpl implements RecordService {
	RecordDAO dao; 
	FoodDAO foodDao; 
	
	@Autowired
	public RecordServiceImpl(RecordDAO dao, FoodDAO foodDao) {
		super();
		this.dao = dao;
		this.foodDao = foodDao;
	}
	

	@Override
	public RecordDTO findLatestRecord(int memberSEQ) {
		return dao.findLatestRecord(memberSEQ);
	}

	

	@Override
	public RecordDTO findTodayRecord(int memberSEQ) {
		return dao.findTodayRecord(memberSEQ);
	}

	@Override
	public List<RecordDTO> findMonthlyRecord(int memberSEQ, int year, int month) {
		return dao.findMonthlyRecord(memberSEQ, year, month);
	}

	@Override
	public int updateCardio(RecordDTO record) {
		
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		
		// 총 운동 시간: (원래 총 운동 시간 - 원래 유산소 운동 시간) + 업데이트 된 유산소 운동 시간 
		int exerciseTotalTime = originalRecord.getTotalExerciseTime() 
									- originalRecord.getCardioObj().getTotalTime() + record.getCardioObj().getTotalTime(); 
		
		
		record.setTotalExerciseTime(exerciseTotalTime);
		
		return dao.updateCardio(record);
	}

	@Override
	public int updateFitness(RecordDTO record) {
		
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		
		// 총 운동 시간: (원래 총 운동 시간 - 원래 무산소 운동 시간) + 업데이트 된 무산소 운동 시간 
		int exerciseTotalTime = originalRecord.getTotalExerciseTime() 
				- originalRecord.getFitnessObj().getTotalTime() + record.getFitnessObj().getTotalTime(); 
		
		
		record.setTotalExerciseTime(exerciseTotalTime);
		
		return dao.updateFitness(record);
	}

	@Override
	public int updateFood(RecordDTO record) {
		System.out.println("In updateFood Service Impl"); 
		
		// Record 객체에서 FoodObj 발췌 및 Food 테이블 참조를 위해 SEQ만 모은 리스트(foodSEQList) 생성
		List<FoodObj> recordFoodObjList = record.getFoodObj(); 
		List<String> foodSEQList = new ArrayList<String>(); 
		
		// 기록에 저장된 음식 정보 가져와서 foods에 저장
		for(FoodObj food : recordFoodObjList) {
			foodSEQList.add(Integer.toString(food.getFoodSEQ()));  
		} 
		List<FoodDTO> foods = foodDao.findFoodListByPK(foodSEQList);
		
		int ratio = 1; 
		int totalCalory = 0;
		int calory = 0;
	 
		record.setTotalCalory(0);
		
		for(int i=0; i<recordFoodObjList.size(); i++) {
			FoodDTO food = foods.get(i);
			
			// (섭취량)/(1회 제공량) 구하기 
			ratio = (recordFoodObjList.get(i).getQuantity())/(food.getQuantity());
			
			// totalCalory, 각 음식의 칼로리 두 곳에 반영해야 하므로 각 음식의 칼로리만 별도 선언. 
			calory = (int)food.getCalory()*ratio;
			
			// record 의 totalCalory에 누적
			totalCalory+=calory; 
			
			// recordFoodObjList에 반영
			recordFoodObjList.get(i).setCalory(calory);
			recordFoodObjList.get(i).setCarb((int)food.getCarb()*ratio);
			recordFoodObjList.get(i).setProtein((int)food.getProtein()*ratio);
			recordFoodObjList.get(i).setFat((int)food.getFat()*ratio);
			recordFoodObjList.get(i).setCholesterol((int)food.getCholesterol()*ratio);	
		} 
		
		// 계산이 완료된 totalCalory 및 각 음식 기록을 저장.
		record.setTotalCalory(totalCalory);
		record.setFoodObj(recordFoodObjList);
		
		return dao.updateFood(record);
	}

	@Override
	public int updateStatus(RecordDTO record) {
		return dao.updateStatus(record);
	}
}
