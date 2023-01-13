package com.multi.drd.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multi.drd.json.CardioObj;
import com.multi.drd.json.FitnessObj;
import com.multi.drd.personalroutine.PersonalRoutineDTO;
import com.multi.drd.routine.RoutineDTO;

/* 
 * RoutineDTO 내 cardioList, fitnessList 와 
 * PersonalRoutineDTO 내 cardioList, fitnessList의 
 * 형태가 동일하므로 오버로딩으로 구현한다.  
 */

public class JsonUtils {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	//RoutineDTO 관련 
	public static CardioObj parseCardioList(RoutineDTO routine) {
		 
		CardioObj cardioList = null; 
		
		try {
			cardioList = mapper.readValue(routine.getCardioObj(), CardioObj.class);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cardioList; 
	} 
	
	public static FitnessObj parseFitnessList(RoutineDTO routine) {
		 
		FitnessObj fitnessList = null; 
		String fitnessString = routine.getFitnessObj();
		
		
		try {
			fitnessList = mapper.readValue(fitnessString, FitnessObj.class);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fitnessList; 
	} 
	
	// PersonalRoutineDTO 관련 - Cardio
	public static CardioObj parseCardioList(PersonalRoutineDTO routine) {
		 
		CardioObj cardioList = null; 
		
		try {
			cardioList = mapper.readValue(routine.getCardioObj(), CardioObj.class);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cardioList; 
	} 
	
	// Object => String으로 변환
	public static String convertToString(CardioObj obj) {
		String str = null; 
		
		try {
			str = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return str;
	} 

	// cardioObj에 cardio 추가 
	public static void insertCardio(CardioObj obj, CardioObj.CardioList cardio) {
		List<CardioObj.CardioList> list = obj.getCardioList(); 
		
		list.add(cardio);  
		updateTotalTime(obj); 
		obj.setCardioList(list);
	}
	
	// 조회
	public static int getIndexBySEQ(CardioObj obj, int cardioSEQ) {
		int index = -1;
		List<CardioObj.CardioList> list = obj.getCardioList();
		
		for (int i=0; i < list.size(); i++) {
			  if(list.get(i).getCardioSEQ() == cardioSEQ) {
				  index = i; 
				  break;
			  }
		}
		
		return index;
	} 

	public static void updateBySEQ(CardioObj obj, int cardioSEQ, String key, int value) {
		
		int index = getIndexBySEQ(obj, cardioSEQ);  
		
		if(index < 0) {
			System.out.println("No Object is found");
			return;
		}
		
		if(key.equals("cardioSEQ")) {
			obj.getCardioList().get(index).setCardioSEQ(value);
		}
		else if(key.equals("time")) {
			obj.getCardioList().get(index).setTime(value);  
			updateTotalTime(obj);
			
		} 
		else if(key.equals("calory")) {
			obj.getCardioList().get(index).setCalory(value);
		}	
	}   
	
	public static void updateTotalTime(CardioObj obj) {
		int totalTime = 0; 
		
		List<CardioObj.CardioList> list = obj.getCardioList(); 
		
		for(CardioObj.CardioList c : list) {
			totalTime += c.getTime();
		}
		obj.setTotalTime(totalTime);	
	} 
	
	public static void deleteBySEQ(CardioObj obj, int cardioSEQ) {	
		
		int index = getIndexBySEQ(obj, cardioSEQ);  
		
		if(index < 0) {
			System.out.println("No Object is found");
			return;
		}
		obj.getCardioList().remove(index);
		updateTotalTime(obj);		
	} 
	
	// PersonalRoutineDTO 관련 - cardio 끝 
	
	// PersonalRoutineDTO 관련 - fitness 
	public static FitnessObj parseFitnessList(PersonalRoutineDTO routine) {
		 
		FitnessObj fitnessList = null; 
		String fitnessString = routine.getFitnessObj();
		
		try {
			fitnessList = mapper.readValue(fitnessString, FitnessObj.class);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fitnessList; 
	}  
	
	public static String convertToString(FitnessObj obj) {
		String str = null; 
		
		try {
			str = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return str;
	}  
	
	// fitnessObj에 fitness 추가 
	public static void insertFitness(FitnessObj obj, FitnessObj.FitnessList fitness) {
		List<FitnessObj.FitnessList> list = obj.getFitnessList(); 
		
		list.add(fitness);  
		obj.setFitnessList(list);
	}
	
	// 조회
	public static int getIndexBySEQ(FitnessObj obj, int fitnessSEQ) {
		int index = -1;
		List<FitnessObj.FitnessList> list = obj.getFitnessList();
		
		for (int i=0; i < list.size(); i++) {
			  if(list.get(i).getFitnessSEQ() == fitnessSEQ) {
				  index = i; 
				  break;
			  }
		}
		return index;
	} 

	public static void updateBySEQ(FitnessObj obj, int fitnessSEQ, String key, int value) {
		
		int index = getIndexBySEQ(obj, fitnessSEQ); 
		
		if(index < 0) {
			System.out.println("No Object is found");
			return;
		}
		
		if(key.equals("fitnessSEQ")) {
			obj.getFitnessList().get(index).setFitnessSEQ(value);
		}
		else if(key.equals("set")) {
			obj.getFitnessList().get(index).setSet(value);  
		} 
		else if(key.equals("count")) {
			obj.getFitnessList().get(index).setCount(value);
		}	
		else if(key.equals("weight")) {
			obj.getFitnessList().get(index).setWeight(value);
		}	
	}   
	
	public static void deleteBySEQ(FitnessObj obj, int fitnessSEQ) {	
		
		int index = getIndexBySEQ(obj, fitnessSEQ);  
		
		if(index < 0) {
			System.out.println("No Object is found");
			return;
		}
		
		obj.getFitnessList().remove(index);		
	} 
}
