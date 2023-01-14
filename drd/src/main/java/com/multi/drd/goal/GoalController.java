package com.multi.drd.goal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.drd.json.CardioObj;
import com.multi.drd.json.FitnessObj;
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
		GoalDTO goal = service.readGoal(member.getMemberSEQ());
		
		model.addAttribute("member", member);
		model.addAttribute("goal", goal);
		//오늘 기록
//		RecordDTO records = recordservice.findTodayRecord(member.getMemberSEQ());
//		int todayExerciseTime = records.getTotalExerciseTime();
//		System.out.println(records);
//		System.out.println(todayExerciseTime);
//
//		model.addAttribute("todayExerciseTime", todayExerciseTime);
		
		PersonalRoutineDTO pRoutine = prservice.findOne();
		CardioObj cardioObj = JsonUtils.parseCardioList(pRoutine); 
		FitnessObj fitnessObj = JsonUtils.parseFitnessList(pRoutine); 
		
		int cardioTotalTime = cardioObj.getTotalTime();
		int fitnessTotalTime = fitnessObj.getTotalTime();
		
		model.addAttribute("cardioTotalTime", cardioTotalTime);
		model.addAttribute("fitnessTotalTime", fitnessTotalTime);
		
		return "goal/goal";
	}
	
	@RequestMapping(value = "/updateTime",method = RequestMethod.POST)
	public String updategoalTime(GoalDTO goal) {
		int result = service.updateTime(goal);
		System.out.println(result);
		return "redirect:/goal/readAll";
	}
	
//	@RequestMapping(value = "/updateCalory",method = RequestMethod.POST)
//	public String updategoalCalory(GoalDTO goal) {
//		int result = service.updateCalory(goal);
//		System.out.println(result);
//		return "redirect:/goal/readAll";
//	}
//	
}
