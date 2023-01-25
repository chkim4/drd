package com.multi.drd.personalroutine;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.drd.cardio.CardioDTO;
import com.multi.drd.cardio.CardioService;
import com.multi.drd.fitness.FitnessDTO;
import com.multi.drd.fitness.FitnessService;
import com.multi.drd.json.CardioObj;
import com.multi.drd.json.CardioObj.CardioList;
import com.multi.drd.json.FitnessObj;
import com.multi.drd.json.FitnessObj.FitnessList;
import com.multi.drd.member.MemberDTO;
import com.multi.drd.routine.RoutineDTO;
import com.multi.drd.routine.RoutineService;
import com.multi.drd.utils.JsonUtils;

@Controller
@RequestMapping("/personalroutine")
public class PersonalRoutineController {
	
	private PersonalRoutineService service;
	private CardioService cardioservice;
	private FitnessService fitnessservice;
	private RoutineService routineservice;
	
	public PersonalRoutineController() {} 
	
	@Autowired
	public PersonalRoutineController(PersonalRoutineService service, CardioService cardioservice, FitnessService fitnessservice, RoutineService routineservice) {
		this.service = service;
		this.cardioservice = cardioservice;
		this.fitnessservice = fitnessservice;
		this.routineservice = routineservice;
	}
	
	/* 현재 사용자의 personalRoutine 조회.
	 * 230110 현재 cardioList와 fitnessList 파싱 테스트를 위해 personalRoutineSEQ = 2 인 레코드를 조회하도록 구현함. 
	 * personalRoutineDTO 내 cardioList와 fitnessList를 파싱하는 예제 포함. 
	 */
	@RequestMapping(value = "/findOne.do",method = RequestMethod.GET)
	public String findOne() {
		
		PersonalRoutineDTO pRoutine = service.findOne(); 
		
		CardioObj cardioObj = JsonUtils.parseCardioList(pRoutine); 
		FitnessObj fitnessObj = JsonUtils.parseFitnessList(pRoutine);  
		
		System.out.println("cardioObj before parsing: " + pRoutine.getCardioObj()); 
		System.out.println("cardioObj: "+ cardioObj);
		System.out.println("cardioObj totalTime: " + cardioObj.getTotalTime());
		System.out.println("cardioList in cardioObj: " + cardioObj.getCardioList());
		System.out.println("-----------------------");
		System.out.println("fitnessList before parsing: " + pRoutine.getFitnessObj());
		System.out.println("fitnessList totalTime: " + fitnessObj.getTotalTime());
		System.out.println("fitnessList fitnesses: " + fitnessObj.getFitnessList()); 
		System.out.println("-----------------------");
		System.out.println("cardioList to String: " + JsonUtils.convertToString(cardioObj));
		System.out.println("fitnessList to String: " + JsonUtils.convertToString(fitnessObj));
		
		return "some/dummy";
	}  

	/* 현재 사용자의 personalRoutine 업데이트.
	 * 230110 현재 cardioList와 fitnessList를 string 으로 전환 후 
	 * 저장되는 것을 확인하기 위해 personalRoutineSEQ = 1 인 레코드를 갱신하도록 구현함.  
	 */
	@RequestMapping(value = "/updateOne.do",method = RequestMethod.GET)
	public String updateOne() {
		
		PersonalRoutineDTO pRoutine = service.findOne(); 
		
		//  -------------------- Cardio 관련 --------------------  
		// String -> Object
		CardioObj cardioObj = JsonUtils.parseCardioList(pRoutine); 
		 
		// 추가 (cardioSEQ, time, calory) 
		System.out.println("Before Insert: " + cardioObj.getCardioList());
		CardioObj.CardioList cardio = new CardioObj.CardioList(3,30,100); // seq, time, cal
		JsonUtils.insertCardio(cardioObj, cardio); 
		System.out.println("After Insert: " + cardioObj.getCardioList());
		
		// cardioSEQ 값으로 조회
		int index = JsonUtils.getIndexBySEQ(cardioObj, 1);
		System.out.println("index of cardioSEQ == 1 in cardioList: " + index); 
		
		// cardioSEQ 값으로 업데이트
		System.out.println("---------------");
		System.out.println("Before update: " + cardioObj.getCardioList().get(0));
		JsonUtils.updateBySEQ(cardioObj, 2, "time", 120);  
		System.out.println("After update: " + cardioObj.getCardioList().get(0));

		System.out.println("Before delete: " + cardioObj.getCardioList());
		JsonUtils.deleteBySEQ(cardioObj, 2);  
		System.out.println("After delete: " + cardioObj.getCardioList()); 
		
		// Object -> String
		String cardios = JsonUtils.convertToString(cardioObj); 
		pRoutine.setCardioObj(cardios);
		
	//  -------------------- Fitness 관련 -------------------- 
		System.out.println("--------------------Fitness--------------------");
		// String -> Object
		FitnessObj fitnessObj = JsonUtils.parseFitnessList(pRoutine);
		
		// 추가 (fitnessSEQ, set, count, weight) 
		System.out.println("Before Insert: " + fitnessObj.getFitnessList());
		FitnessObj.FitnessList fitness = new FitnessObj.FitnessList(3, 3,30,10); 
		JsonUtils.insertFitness(fitnessObj, fitness); 
		System.out.println("After Insert: " + fitnessObj.getFitnessList());
		
		// cardioSEQ 값으로 조회
		index = JsonUtils.getIndexBySEQ(fitnessObj, 1);
		System.out.println("index of cardioSEQ == 1 in cardioList: " + index); 
		
		// cardioSEQ 값으로 업데이트
		System.out.println("---------------");
		System.out.println("Before update: " + fitnessObj.getFitnessList().get(0));
		JsonUtils.updateBySEQ(fitnessObj, 1, "count", 5);  
		System.out.println("After update: " + fitnessObj.getFitnessList().get(0));

		System.out.println("Before delete: " + fitnessObj.getFitnessList());
		JsonUtils.deleteBySEQ(cardioObj, 1);  
		System.out.println("After delete: " + fitnessObj.getFitnessList()); 
		
		
		// Object -> String
		String fitnesses = JsonUtils.convertToString(fitnessObj);
		pRoutine.setFitnessObj(fitnesses);
		
		// DB 처리: 1이면 성공
		System.out.println("result of update: " + service.updateOne(pRoutine));
		
		return "some/dummy";
	}  
	//test http://localhost:8088/personalroutine/setpage
		@RequestMapping(value = "/setpage", method = RequestMethod.GET)
		public String setRoutinePage(Model model, HttpSession session) {
			MemberDTO member = (MemberDTO)session.getAttribute("member");
			int personalRoutineSEQ = member.getPersonalRoutineSEQ();
			model.addAttribute("member", member);
			//자신 루틴
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			CardioObj myCardioObj = JsonUtils.parseCardioList(pRoutine); 
			FitnessObj myFitnessObj = JsonUtils.parseFitnessList(pRoutine);  
			List<CardioList> myCardioList = myCardioObj.getCardioList();
			model.addAttribute("myroutine_cardioList", myCardioList);
			List<FitnessList> myFitnessList = myFitnessObj.getFitnessList();
			model.addAttribute("myroutine_fitnessList", myFitnessList);

			//기본 루틴
			RoutineDTO routine = routineservice.findBySEQ(pRoutine.getRoutineSEQ());
			model.addAttribute("routine", routine);
			CardioObj cardioObj = JsonUtils.parseCardioList(pRoutine); 
			FitnessObj fitnessObj = JsonUtils.parseFitnessList(pRoutine);  
			List<CardioList> cardioList = cardioObj.getCardioList();
			model.addAttribute("routine_cardioList", cardioList);
			List<FitnessList> fitnessList = fitnessObj.getFitnessList();
			model.addAttribute("routine_fitnessList", fitnessList);
			
			//진행중 루틴(이름)
			String routineName = routine.getName();
			int routinePeriod = routine.getPeriod();
			Date now = new Date();
			
			//일 진행중
			double days = (now.getTime() - pRoutine.getCreatedAt().getTime())/(1000*24*60*60);
			model.addAttribute("days", Double.valueOf(days).intValue());
			
			//진행률
			double progress = Math.round((days/(routinePeriod*7))*10000)/100.0 ;
			model.addAttribute("progress", progress);
			
//			System.out.println(cardioservice.findOne(cardiolist.get(0).getCardioSEQ()).getClass());
//			List<CardioDTO> cardios = new ArrayList<CardioDTO>();
//			for (int i = 0; i < cardiolist.size(); i++) {
//				cardios.add(cardioservice.findOne(cardiolist.get(i).getCardioSEQ()));
//			}
//			List<FitnessDTO> fitnesses = new ArrayList<FitnessDTO>();
//			for (int i = 0; i < fitnesslist.size(); i++) {
//				fitnesses.add(fitnessservice.findOne(fitnesslist.get(i).getFitnessSEQ()));
//			}
//			
//			pRoutine.getCreatedAt().getClass();
			return "personalroutine/routineset";
			
		}
		//루틴 누를 시 ajax로 지신의 personalroutine 표시(fitness)
		@RequestMapping(value = "/ajax/setfitnesslist", produces = "application/json;charset=utf-8")
		@ResponseBody
		public List<FitnessList> SetfitnessList(int personalRoutineSEQ) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			FitnessObj myFitnessObj = JsonUtils.parseFitnessList(pRoutine);
			List<FitnessList> myFitnessList = myFitnessObj.getFitnessList();
			return myFitnessList;
			
		}
		//루틴 누를 시 ajax로 지신의 personalroutine 표시(cardio)
		@RequestMapping(value = "/ajax/setcardiolist", produces = "application/json;charset=utf-8")
		@ResponseBody
		public List<CardioList> setCardioList(int personalRoutineSEQ) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			CardioObj myCardioObj = JsonUtils.parseCardioList(pRoutine); 
			List<CardioList> myCardioList = myCardioObj.getCardioList();
			return myCardioList;
			
		}
		//루틴 수정 띄우기(fitness)
		@RequestMapping(value = "/ajax/setfitness", produces = "application/json;charset=utf-8")
		@ResponseBody
		public FitnessDTO readFitness(int fitnessSEQ) {
			return fitnessservice.findOne(fitnessSEQ);
		}
		//루틴 수정 띄우기(fitnessList)
		@RequestMapping(value = "/ajax/setfitnesschange", produces = "application/json;charset=utf-8")
		@ResponseBody
		public FitnessList readFitnessList(int fitnessSEQ, int personalRoutineSEQ) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			FitnessObj myFitnessObj = JsonUtils.parseFitnessList(pRoutine);
			int index = JsonUtils.getIndexBySEQ(myFitnessObj, fitnessSEQ);
			FitnessList myFitnessList = myFitnessObj.getFitnessList().get(index);
			return myFitnessList;
		}
		//루틴 수정 띄우기(cardio)
		@RequestMapping(value = "/ajax/setcardio", produces = "application/json;charset=utf-8")
		@ResponseBody
		public CardioDTO readCaldio(int cardioSEQ) {
			return cardioservice.findOne(cardioSEQ);
		}
		//루틴 수정 띄우기(CardioList)
		@RequestMapping(value = "/ajax/setcardiochange", produces = "application/json;charset=utf-8")
		@ResponseBody
		public CardioList readCardioList(int cardioSEQ, int personalRoutineSEQ) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			CardioObj myCardioObj = JsonUtils.parseCardioList(pRoutine);
			int index = JsonUtils.getIndexBySEQ(myCardioObj, cardioSEQ);
			CardioList myCardiosList = myCardioObj.getCardioList().get(index);
			return myCardiosList;
		}
		
		//fitnessObj 업데이트
		@RequestMapping(value = "/updatefitness", method = RequestMethod.POST)
		public void updatefitness(int personalRoutineSEQ, int fitnessSEQ, int set, int count, int weight) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			FitnessObj myFitnessObj = JsonUtils.parseFitnessList(pRoutine);
			JsonUtils.updateBySEQ(myFitnessObj, fitnessSEQ, "set", set);
			JsonUtils.updateBySEQ(myFitnessObj, fitnessSEQ, "count", count);
			JsonUtils.updateBySEQ(myFitnessObj, fitnessSEQ, "weight", weight);
			String fitnessObj = JsonUtils.convertToString(myFitnessObj);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personalRoutineSEQ", personalRoutineSEQ);
			map.put("fitnessObj", fitnessObj);
			service.updatefitness(map);
		}
		
		//cardioObj 업데이트 하며 전체 시간 변경
		@RequestMapping(value = "/updatecardio", method = RequestMethod.POST)
		public void updatecardio(int personalRoutineSEQ, int cardioSEQ, int time) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			CardioObj myCardioObj = JsonUtils.parseCardioList(pRoutine);
			JsonUtils.updateBySEQ(myCardioObj, cardioSEQ, "time", time);
			int cardiototaltime = 0;
			for (int i = 0; i < myCardioObj.getCardioList().size(); i++) {
				cardiototaltime += myCardioObj.getCardioList().get(i).getTime();
			}
			myCardioObj.setTotalTime(cardiototaltime);
			String cardioObj = JsonUtils.convertToString(myCardioObj);
			System.out.println(cardioObj);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personalRoutineSEQ", personalRoutineSEQ);
			map.put("cardioObj", cardioObj);
			service.updatecardio(map);
		}
		
		//ck additional fitness
		public boolean checkaddtionalfitness(int personalRoutineSEQ, int fitnessSEQ) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			RoutineDTO routine = routineservice.findBySEQ(pRoutine.getRoutineSEQ());
			FitnessObj fitobj = JsonUtils.parseFitnessList(routine);
			int fitlistsize = fitobj.getFitnessList().size();
			int fitindex = JsonUtils.getIndexBySEQ(fitobj, fitnessSEQ);
			return fitlistsize > fitindex;
		}
		//ck additional cardio
		public boolean checkaddtionalcardio(int personalRoutineSEQ, int cardioSEQ) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			RoutineDTO routine = routineservice.findBySEQ(pRoutine.getRoutineSEQ());
			CardioObj carobj = JsonUtils.parseCardioList(routine);
			int carlistsize = carobj.getCardioList().size();
			int carindex = JsonUtils.getIndexBySEQ(carobj, cardioSEQ);
			return carlistsize > carindex;
		}
		
		//tof fitness
		@RequestMapping(value = "/findfitnessbymusclegroup", produces = "application/json;charset=utf-8")
		@ResponseBody
		public List<FitnessDTO> findFDTOByMG(int personalRoutineSEQ, String muscleGroup, int fitnessSEQ) {
			List<FitnessDTO> result = null;
			if(checkaddtionalfitness(personalRoutineSEQ, fitnessSEQ)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("musclegroup", muscleGroup);
				map.put("fitnessSEQ", fitnessSEQ);
				result = fitnessservice.findbymusclegroupwithseq(map);
				System.out.println(result);
				if(result.size()==0) {
					FitnessDTO check = new FitnessDTO(fitnessSEQ, "0", "0", "0");
					result.add(check);
				}
			}else {
				FitnessDTO check = new FitnessDTO(0, "0", "0", "0");
				result.add(check);
			}
			return result;
		}
		
		//tof cardio
		@RequestMapping(value = "/findcardiobyintensity", produces = "application/json;charset=utf-8")
		@ResponseBody
		public List<CardioDTO> findCDTOByI(int personalRoutineSEQ , int intensity, int cardioSEQ) {
			List<CardioDTO> result = null;
			if(checkaddtionalcardio(personalRoutineSEQ, cardioSEQ)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("intensity", intensity);
				map.put("cardioSEQ", cardioSEQ);
				result = cardioservice.findByIntensitywithseq(map);
				if(result.size()==0) {
					CardioDTO check = new CardioDTO(cardioSEQ, "0", 0, 0);
					result.add(check);
				}else {
					CardioDTO check = new CardioDTO(0, "0", 0, 0);
					result.add(check);					
				}
			}
			return result;
		}
		//delete
		
		public void deletefitness(int personalRoutineSEQ, int fitnessSEQ) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			FitnessObj myFitnessObj = JsonUtils.parseFitnessList(pRoutine);
			JsonUtils.deleteBySEQ(myFitnessObj, fitnessSEQ);
			String fitnessObj = JsonUtils.convertToString(myFitnessObj);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personalRoutineSEQ", personalRoutineSEQ);
			map.put("fitnessObj", fitnessObj);
			service.updatefitness(map);
		}
		public void deleteacardio(int personalRoutineSEQ, int cardioSEQ) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			CardioObj myCardioObj = JsonUtils.parseCardioList(pRoutine);
			JsonUtils.deleteBySEQ(myCardioObj, cardioSEQ);
			String cardioObj = JsonUtils.convertToString(myCardioObj);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personalRoutineSEQ", personalRoutineSEQ);
			map.put("cardioObj", cardioObj);
			service.updatefitness(map);
		}
		
		//delete and update
		@RequestMapping(value="/DnUF.do", method = RequestMethod.POST)
		public void deleteandupdatefitness(int personalRoutineSEQ, int beforefitnessSEQ, int afterfitnessSEQ, int set, int count, int weight) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			FitnessObj myFitnessObj = JsonUtils.parseFitnessList(pRoutine);
			int index = JsonUtils.getIndexBySEQ(myFitnessObj, beforefitnessSEQ);
			FitnessDTO fit = fitnessservice.findOne(afterfitnessSEQ);
			FitnessObj.FitnessList afterFitnessObj = new FitnessList(afterfitnessSEQ, set, count, weight);
			afterFitnessObj.setName(fit.getName());
			List<FitnessObj.FitnessList> myFitnessList = myFitnessObj.getFitnessList();
			myFitnessList.add(index, afterFitnessObj);
			myFitnessList.remove(index+1);
			myFitnessObj.setFitnessList(myFitnessList);
			String fitnessObj = JsonUtils.convertToString(myFitnessObj);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personalRoutineSEQ", personalRoutineSEQ);
			map.put("fitnessObj", fitnessObj);
			service.updatefitness(map);
			
		}
		@RequestMapping(value="/DnUC.do", method = RequestMethod.POST)
		public void deleteandupdatecardio(int personalRoutineSEQ, int beforecardioSEQ, int aftercardioSEQ, int time, int calory) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			CardioObj myCardioObj = JsonUtils.parseCardioList(pRoutine);
			int index = JsonUtils.getIndexBySEQ(myCardioObj, beforecardioSEQ);
			CardioObj.CardioList afterCardioObj = new CardioList(aftercardioSEQ, time, calory);
			List<CardioObj.CardioList> myCardioList = myCardioObj.getCardioList();
			myCardioList.add(index, afterCardioObj);
			myCardioList.remove(index+1);
			myCardioObj.setCardioList(myCardioList);
			String cardioObj = JsonUtils.convertToString(myCardioObj);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personalRoutineSEQ", personalRoutineSEQ);
			map.put("cardioObj", cardioObj);
			service.updatefitness(map);
		}
		@RequestMapping(value = "/searchFbyname", produces = "application/json;charset=utf-8")
		@ResponseBody
		public List<FitnessDTO> findFitnessbyName(String name) {
			return fitnessservice.findbyname(name);
		}
		@RequestMapping(value = "/searchCbyname", produces = "application/json;charset=utf-8")
		@ResponseBody		
		public List<CardioDTO> findCardiobyName(String name) {
			return cardioservice.findbyname(name);
		}
		@RequestMapping(value = "/AF.do", method = RequestMethod.POST)
		public void insertfitness(int personalRoutineSEQ, int fitnessSEQ, int set, int count, int weight) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			FitnessObj myFitnessObj = JsonUtils.parseFitnessList(pRoutine);
			FitnessDTO fit = fitnessservice.findOne(fitnessSEQ);
			FitnessObj.FitnessList FitnessList = new FitnessList(fitnessSEQ, set, count, weight);
			FitnessList.setName(fit.getName());
			JsonUtils.insertFitness(myFitnessObj, FitnessList);
			String fitnessObj = JsonUtils.convertToString(myFitnessObj);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personalRoutineSEQ", personalRoutineSEQ);
			map.put("fitnessObj", fitnessObj);
			service.updatefitness(map);
		}
		
		@RequestMapping(value = "/AC.do", method = RequestMethod.POST)
		public void insertcardio(int personalRoutineSEQ, int cardioSEQ, int time, int calory) {
			PersonalRoutineDTO pRoutine = service.findOne1(personalRoutineSEQ);
			CardioObj myCardioObj = JsonUtils.parseCardioList(pRoutine);
			CardioObj.CardioList CardioList = new CardioList(cardioSEQ, time, calory);
			CardioDTO car = cardioservice.findOne(cardioSEQ);
			CardioList.setName(car.getName());
			CardioList.setIntensity(car.getIntensity());
			JsonUtils.insertCardio(myCardioObj, CardioList);
			String cardioObj = JsonUtils.convertToString(myCardioObj);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("personalRoutineSEQ", personalRoutineSEQ);
			map.put("cardioObj", cardioObj);
			service.updatefitness(map);
		}
}
