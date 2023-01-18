package com.multi.drd.dashboard;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multi.drd.goal.GoalDTO;
import com.multi.drd.goal.GoalService;
import com.multi.drd.json.CardioObj;
import com.multi.drd.json.FitnessObj;
import com.multi.drd.json.FoodObj;
import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
import com.multi.drd.memberbio.MemberBioService;
import com.multi.drd.record.RecordDTO;
import com.multi.drd.record.RecordService;
import com.multi.drd.utils.JsonUtils;

@Controller
@RequestMapping("/dashboard")
@SessionAttributes("member")
public class DashboardController {
	DashboardService service;
	RecordService recordService;
	MemberBioService memberBioService;
	GoalService goalService;
	
	public DashboardController() {
		super();
	}
	
	@Autowired
	public DashboardController(DashboardService service, RecordService recordService, MemberBioService memberBioService,GoalService goalService) {
		super();
		this.service = service;
		this.recordService = recordService;
		this.memberBioService = memberBioService;
		this.goalService = goalService;
	}


	@RequestMapping("/read")
	public String read(Model model, HttpSession session) throws ParseException {
		//세션멤버정보
		MemberDTO member = (MemberDTO) session.getAttribute("member");  
		System.out.println("member from sesseion: " + member);
		//멤버프로필
		MemberBioDTO memberBio = memberBioService.findByPK(member.getMemberSEQ());
		//가장최근기록
		RecordDTO latestRecord = recordService.findLatestRecord(member.getMemberSEQ());
		System.out.println(latestRecord);
		//recordService.ExcerciseHourByWeek(member.getMemberSEQ());
		
		//List<RecordDTO> recordMonthly = recordService.findMonthlyRecord(member.getMemberSEQ(), 2022, 12);
		//System.out.println("excerciseHourWeekly" +excerciseHourWeekly);
		//System.out.println("recordMonthly" +recordMonthly);
		
		
		/*
		 * List<FitnessObj> fitnessObjMonthly = new ArrayList<FitnessObj>();
		 * List<CardioObj> cardioObjMonthly = new ArrayList<CardioObj>(); List<Date>
		 * exerciseDateMonthly = new ArrayList<Date>(); List<String>
		 * formattedExerciseDateMonthly = new ArrayList<String>(); List<Integer>
		 * fitnessTimeMonthly = new ArrayList<Integer>(); List<Integer>
		 * cardioTimeMonthly = new ArrayList<Integer>();
		 * 
		 * for(int i=0;i<recordMonthly.size();i++) {
		 * exerciseDateMonthly.add(recordMonthly.get(i).getDate());
		 * fitnessObjMonthly.add(recordMonthly.get(i).getFitnessObj());
		 * cardioObjMonthly.add(recordMonthly.get(i).getCardioObj()); } String input =
		 * "Thu Jun 18 20:56:02 EDT 2009"; SimpleDateFormat parser = new
		 * SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
		 * 
		 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); for(int i=0;
		 * i<exerciseDateMonthly.size(); i++) {
		 * formattedExerciseDateMonthly.add(formatter.format(exerciseDateMonthly.get(i))
		 * ); }
		 * 
		 * 
		 * 
		 * for(int i=0;i<fitnessObjMonthly.size();i++) {
		 * fitnessTimeMonthly.add(fitnessObjMonthly.get(i).getTotalTime()); } for(int
		 * i=0;i<fitnessObjMonthly.size();i++) {
		 * cardioTimeMonthly.add(cardioObjMonthly.get(i).getTotalTime()); }
		 * 
		 * System.out.println(exerciseDateMonthly);
		 * System.out.println(fitnessTimeMonthly);
		 * System.out.println(cardioTimeMonthly);
		 * System.out.println(formattedExerciseDateMonthly);
		 */
		
		List<RecordDTO> recordWeekly = service.findByWeek(member.getMemberSEQ());
		System.out.println("recordWeekly " + recordWeekly.size());
		
		/* 운동횟수에 따른 목표단백질 설정 *///---------------------------------------------------------goalController 이동 recordWeekly도 복사해야
		Map<String, Integer> amountAndSeq = new HashMap<String, Integer>();
		if(recordWeekly.size() < 3) {
			amountAndSeq.put("goalProtein", (int) (memberBio.getWeight()*0.8*7));
			amountAndSeq.put("memberSEQ", member.getMemberSEQ());
		} else if(recordWeekly.size() < 6) {
			amountAndSeq.put("goalProtein", (int) (memberBio.getWeight()*1.0*7));
			amountAndSeq.put("memberSEQ", member.getMemberSEQ());		
			}
		else {
			amountAndSeq.put("goalProtein", (int) (memberBio.getWeight()*1.2*7));
			amountAndSeq.put("memberSEQ", member.getMemberSEQ());
		}
		System.out.println(amountAndSeq);
		goalService.updateProtein(amountAndSeq);
		//----------------------------------------------------------------------------------------
		
		/*
		 * 목표 단백질readGoal, 실제 섭취 단백질recordWeekly>FoodObj>protein 비교.
		 */		
		//GoalDTO readGoal = goalService.readGoal(member.getMemberSEQ());
		//int goalProtein = readGoal.getGoalProtein();
		//int goalCalory = readGoal.getGoalCalory();
		
		
		//목표 프로틴, 목표 칼로리 목표테이블, 섭취프로틴, 섭취칼로리 ajax
		/*
		 * List<FoodObj> foodlist = null;
		 * 
		 * for(int i=0;i<recordWeekly.size();i++) { foodlist =
		 * recordWeekly.get(i).getFoodObj(); } System.out.println(foodlist); int
		 * takeProtein = 0; int totalCalory = 0; for(int i=0; i<foodlist.size();i++) {
		 * takeProtein += foodlist.get(i).getProtein(); totalCalory +=
		 * foodlist.get(i).getCalory(); }
		 */
		
		
		model.addAttribute("member", member);
		model.addAttribute("memberBio", memberBio);
		model.addAttribute("latestRecord", latestRecord);
		
		model.addAttribute("latestRecord", latestRecord);
	
		return "dashboard/dashboard";
		//return "sample/cards";
	}
	/**/
	@RequestMapping(value = "/read", method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public List<Map<String, Integer>> proteinCaloryList(HttpSession session, Model model) {
		//목표 프로틴, 목표 칼로리 
		GoalDTO readGoal = goalService.readGoal(((MemberDTO)session.getAttribute("member")).getMemberSEQ());
		int goalProtein = readGoal.getGoalProtein();
		int goalCalory = readGoal.getGoalCalory();
		
		//섭취프로틴, 섭취칼로리
		List<RecordDTO> recordWeekly = service.findByWeek(((MemberDTO)session.getAttribute("member")).getMemberSEQ());
		System.out.println("recordWeekly"+recordWeekly);
		List<FoodObj> foodlist = null;
		
		for(int i=0;i<recordWeekly.size();i++) {
			foodlist = recordWeekly.get(i).getFoodObj();
		}
		System.out.println(foodlist);
		int takeProtein = 0;
		int totalCalory = 0;
		for(int i=0; i<foodlist.size();i++) {
			takeProtein += foodlist.get(i).getProtein();
			totalCalory += foodlist.get(i).getCalory();
			
		}
		
		int restProtein = goalProtein - takeProtein;	//적정섭취와 실제섭취 간격
		int restCalory = goalCalory - totalCalory;	//적정섭취와 실제섭취 간격
		
		List<Map<String, Integer>> proteinAndCalory = new ArrayList<Map<String, Integer>>();
		
		Map<String, Integer> protein = new HashMap<String, Integer>();
		protein.put("take", takeProtein);
		protein.put("rest", restProtein);
		protein.put("goal", goalProtein);
		
		Map<String, Integer> calory = new HashMap<String, Integer>();
		calory.put("take", totalCalory);
		calory.put("rest", restCalory);
		calory.put("goal", goalCalory);
		
		proteinAndCalory.add(protein);
		proteinAndCalory.add(calory);
		System.out.println("proteinAndCalory:" + proteinAndCalory);
		 model.addAttribute("proteinAndCalory", proteinAndCalory);
		
		
		return proteinAndCalory;
		


	 }
	@RequestMapping(value = "/readchart", method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> weekChart(HttpSession session, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		/*목표시간*/
		GoalDTO readGoal = goalService.readGoal(((MemberDTO)session.getAttribute("member")).getMemberSEQ());
		int goalTime = readGoal.getGoalExerciseTime();
		/*일주일단위 기록 */
		//List<AggregationResultDTO>excerciseHourWeekly = service.ExcerciseHourByWeek(member.getMemberSEQ());
		
		List<AggregationResultDTO> cardioMinByWeek = service.cardioMinByWeek(((MemberDTO)session.getAttribute("member")).getMemberSEQ());
		List<AggregationResultDTO> fitnessMinByWeek = service.fitnessMinByWeek(((MemberDTO)session.getAttribute("member")).getMemberSEQ());
		List<Integer> goalMinByWeek = new ArrayList<Integer>();
		List<Integer> weeks = new ArrayList<Integer>();
		List<String> dates = new ArrayList<String>();
		
		for(int i=0; i<cardioMinByWeek.size(); i++) {
			goalMinByWeek.add(i, goalTime); 
			// => parsnig을 위한 코드 
			int parsedWeek = JsonUtils.parseTotalExerciseTimeofWeek(cardioMinByWeek.get(i).get_id()).get("week");
			weeks.add(parsedWeek);
		}
		System.out.println("weeks: "+ weeks);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		for(int i=0; i<weeks.size(); i++) {
			cal.set(Calendar.WEEK_OF_YEAR, weeks.get(i));        
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			System.out.println("week format result" + sdf.format(cal.getTime()));
			dates.add(sdf.format(cal.getTime()));
		}
		
		map.put("cardioMinByWeek", cardioMinByWeek);
		map.put("fitnessMinByWeek", fitnessMinByWeek);
		map.put("goalMinByWeek", goalMinByWeek);
		map.put("dates", dates);
		
		return map;
		
	

		
	}
	

}
