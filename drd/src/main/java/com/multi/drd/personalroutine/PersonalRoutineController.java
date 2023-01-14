package com.multi.drd.personalroutine;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.multi.drd.cardio.CardioService;
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
		pRoutine.setCardioList(cardios);
		
	//  -------------------- Fitness 관련 -------------------- 
		System.out.println("--------------------Fitness--------------------");
		// String -> Object
		FitnessObj fitnessObj = JsonUtils.parseFitnessList(pRoutine);
		
		// 추가 (fitnessSEQ, set, count, weight) 
		System.out.println("Before Insert: " + fitnessObj.getFitnessList());
		FitnessObj.FitnessList fitness = new FitnessObj.FitnessList(3,3,30,10); 
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
		pRoutine.setFitnessList(fitnesses);
		
		// DB 처리: 1이면 성공
		System.out.println("result of update: " + service.updateOne(pRoutine));
		
		return "some/dummy";
	}  
	//test http://localhost:8088/personalroutine/setpage
		@RequestMapping(value = "/setpage", method = RequestMethod.GET)
		public String setroutinepage(Model model, HttpSession session) {
			MemberDTO member = (MemberDTO)session.getAttribute("member");
			int personalRoutineSEQ = member.getPersonalRoutineSEQ();
			
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
}
