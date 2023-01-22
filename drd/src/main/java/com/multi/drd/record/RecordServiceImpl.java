package com.multi.drd.record;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.drd.cardio.CardioDTO;
import com.multi.drd.fitness.FitnessDTO;
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
	public List<RecordDTO> findMonthlyRecord(int memberSEQ, int year, int month) {
		return dao.findMonthlyRecord(memberSEQ, year, month);
	} 
	
	//-----------------------------------------유산소(cardio) 관련-----------------------------------------
	@Override
	public List<CardioDTO> findAllCardio() {
		return dao.findAllCardio();
	}
	
	@Override
	public int createCardio(RecordDTO record) {
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		
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
			return dao.updateCardio(record); 
		}
		else {
			return dao.createOne(record);
		}
	}

	@Override
	public int updateCardio(RecordDTO record) {
		
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		
		
		// 총 운동 시간: (원래 총 운동 시간 - 원래 유산소 운동 시간) + 업데이트 된 유산소 운동 시간 
		int exerciseTotalTime = originalRecord.getTotalExerciseTime() 
									- originalRecord.getCardioObj().getTotalTime() + record.getCardioObj().getTotalTime(); 
		
		record.setTotalExerciseTime(exerciseTotalTime); 
		
		int result = 0; 
		
		// 사용자가 모달창에서 모든 목록을 삭제했을 경우 필드 삭제
		if(record.getCardioObj().getTotalTime() == 0) {
			result = dao.deleteField(record, "cardioObj");
		}
		else {
			result = dao.updateCardio(record);
		} 
		
		return result; 
	}
	//-----------------------------------------무산소(fitness) 관련-----------------------------------------
	
	@Override
	public List<FitnessDTO> findAllFitness() {
		return dao.findAllFitness();
	} 
	
	@Override
	public int createFitness(RecordDTO record) {
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		
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
			return dao.updateCardio(record); 
		}
		else {
			return dao.createOne(record);
		}
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
	public int deleteField(RecordDTO record, String field) {
		
		if(field.equals("cardioObj") || field.equals("fitnessObj")) {
			
			RecordDTO originalRecord = dao.findDailyRecord(record.getMemberSEQ(), record.getDate());		
			
			// 총 운동 시간: 유산소 삭제 시: (원래 전체 운동 시간-원래 유산소 시간) / 무산소 삭제 시: (원래 전체 운동 시간-원래 무산소 시간) 
			if(field.equals("cardioObj")) {
				record.setTotalExerciseTime(originalRecord.getTotalExerciseTime() - originalRecord.getCardioObj().getTotalTime());
			}
			else {
				record.setTotalExerciseTime(originalRecord.getTotalExerciseTime() - originalRecord.getFitnessObj().getTotalTime());
			}
		}// if cardioObj || fitnessObj	
			
		return dao.deleteField(record, field);
	}	

}
