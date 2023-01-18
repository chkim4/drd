package com.multi.drd.goal;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.drd.json.CardioObj;
import com.multi.drd.json.CardioObj.CardioList;
import com.multi.drd.json.FitnessObj;
import com.multi.drd.json.FitnessObj.FitnessList;
import com.multi.drd.json.FoodObj;
import com.multi.drd.member.MemberDTO;
import com.multi.drd.personalroutine.PersonalRoutineDTO;
import com.multi.drd.personalroutine.PersonalRoutineService;
import com.multi.drd.record.RecordDTO;
import com.multi.drd.record.RecordService;
import com.multi.drd.utils.JsonUtils;
@Controller
@RequestMapping("/goal")
public class GoalController {
	GoalService service;
	RecordService recordservice;
	PersonalRoutineService prservice;
	
	public GoalController() {}
	
	@Autowired
	public GoalController(GoalService service, RecordService recordservice, PersonalRoutineService prservice) {
		super();
		this.service = service;
		this.recordservice = recordservice;
		this.prservice = prservice;
	}

	@RequestMapping(value = "/readAll",method = RequestMethod.GET)
	public String goalpage(HttpSession session, Model model) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		List<MemberDTO> bodyShapeList = service.getBodyShapeList();
		
		//주간 기록
		List<RecordDTO> weeklyRecord = service.findByWeek(member.getMemberSEQ()); 
		
		// 현재 사용자의 목표 
		GoalDTO goal = service.readGoal(member.getMemberSEQ());
		
		// 현재 사용자의 루틴
		PersonalRoutineDTO pRoutine = prservice.findOne1(member.getPersonalRoutineSEQ());

		int weeklyExerciseTime = 0; 
		int weeklyCalory = 0;
		int weeklyProtein = 0; 

		int goalExerciseTime = goal.getGoalExerciseTime(); // goal 테이블의 goalExerciseTime은 주간 기준임.
		int goalCalory = goal.getGoalCalory()*7; 
		int goalProtein = goal.getGoalProtein()*7; // goal 테이블의 goalProtein과 goalCalory는 하루 기준임.
		
		// => 주간 기록 정보 저장: 총 운동 시간, 총 섭취 칼로리 및 단백질
		if(weeklyRecord != null) { 
			for (RecordDTO record : weeklyRecord) { 
				weeklyExerciseTime+= record.getTotalExerciseTime();
				weeklyCalory += record.getTotalCalory();
				
	        	for (FoodObj food : record.getFoodObj()) {
	        		weeklyProtein += food.getProtein();
	        	}
	        }
		} 
		
		pRoutine.getCardioObj();
		pRoutine.getFitnessObj();

		CardioObj cardioObj = JsonUtils.parseCardioList(pRoutine); //parseCardioList 로 바꾸기
		FitnessObj fitnessObj = JsonUtils.parseFitnessList(pRoutine); 
		
		List<CardioList> cardiolist = cardioObj.getCardioList();
		List<FitnessList> fitnesslist = fitnessObj.getFitnessList();
		
		// 루틴
		model.addAttribute("cardiolist", cardiolist);
		model.addAttribute("fitnesslist", fitnesslist);
				
		model.addAttribute("member", member);
		// model.addAttribute("goal", goal);
		model.addAttribute("bodyShapeList",bodyShapeList);
		// model.addAttribute("cardioTotalTime", cardioTotalTime);
		// model.addAttribute("fitnessTotalTime", fitnessTotalTime);
				
		// 추가사항 
		
		// 기록 관련
		model.addAttribute("weeklyExerciseTime", weeklyExerciseTime);
		model.addAttribute("weeklyCalory", weeklyCalory);
		model.addAttribute("weeklyProtein", weeklyProtein); 
		// 목표 관련
		model.addAttribute("goalExerciseTime", goalExerciseTime); 
		model.addAttribute("goalCalory", goalCalory); 
		model.addAttribute("goalProtein", goalProtein); 
		
		// 루틴 관련
		// 파싱을 완료한 객체
		model.addAttribute("cardioObj", cardioObj); 
		model.addAttribute("fitnessObj", fitnessObj); 
		
		return "goal/goal";
	}
	
	//운동목표 수정하기
	@RequestMapping(value = "/updateTime",method = RequestMethod.POST)
	public String updategoalTime(GoalDTO goal) {
		int result = service.updateTime(goal);
		System.out.println(result);
		return "redirect:/goal/readAll";
	}
	
	//칼로리 수정
	@RequestMapping(value = "/updateCalory",method = RequestMethod.POST)
	public String updategoalCalory(GoalDTO goal) {
		int result = service.updateCalory(goal);
		System.out.println(result);
		return "redirect:/goal/readAll";
	}
	

	//목표체중, 목표체형 수정 ajax
	@RequestMapping(value= "/updateBodyShape" , method = RequestMethod.POST)
	@ResponseBody
	public MemberDTO updateBodyShape(MemberDTO member, HttpSession session) {
		int result = service.updateBodyShape(member);
		System.out.println(result);
		
		return member;
	}
	
}
