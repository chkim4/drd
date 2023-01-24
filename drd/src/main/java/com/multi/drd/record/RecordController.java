package com.multi.drd.record;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.drd.cardio.CardioDTO;
import com.multi.drd.fitness.FitnessDTO;
import com.multi.drd.json.CardioObj;
import com.multi.drd.json.FitnessObj;
import com.multi.drd.json.FoodObj;
import com.multi.drd.member.MemberDTO;
import com.multi.drd.utils.DateUtils;
import com.multi.drd.utils.JsonUtils;

@Controller
@RequestMapping("/record")
public class RecordController { 
	
	RecordService recordService; 

	public RecordController() {
		super();
	}
	
	@Autowired
	public RecordController(RecordService recordService) {
		super();
		this.recordService = recordService;
	} 

	@RequestMapping(value = "/findLatestRecord",method = RequestMethod.GET)  
	@ResponseBody
	public RecordDTO findLatestRecord(){ 
		
		RecordDTO latestRecord = recordService.findLatestRecord(1); 
	
		return latestRecord;
	}
	 
	/* 사용자가 오늘 작성한 기록을 조회.
	 * 없을 경우 null을 반환
	 */
	@RequestMapping(value = "/findTodayRecord.do",method = RequestMethod.GET) 
	@ResponseBody
	public RecordDTO findTodayRecord(HttpSession session){  
		RecordDTO result = recordService.findTodayRecord(1); //memberSEQ가 들어가야 하지만, 테스트 상 1로 함.
		return result;
	}	

	@RequestMapping(value = "/findMonthlyRecord.do",method = RequestMethod.GET) 
	@ResponseBody
	public List<RecordDTO> findMonthlyRecord(HttpSession session, HttpServletRequest request){   
		
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		int year =  Integer.parseInt(request.getParameter("year"));
		int month =  Integer.parseInt(request.getParameter("month"))+1; // JS: 0~11 / Java: 1~12
		
		List<RecordDTO> result = recordService.findMonthlyRecord(member.getMemberSEQ(),year,month);
		return result;
	}	

	@RequestMapping(value = "/index.do",method = RequestMethod.GET) 
	public String indexPage(){ 
		
		return "record/index";
	}	

	@RequestMapping(value = "/createCardio.do",method = RequestMethod.POST) 
	@ResponseBody
	public int createCardio(HttpSession session, HttpServletRequest request){ 
		// 클라이언트로부터 받은 데이터
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		String dateStr = request.getParameter("date"); 		
		String cardioListStr = request.getParameter("cardioList");
		
		// 데이터 가공
		int memberSEQ = member.getMemberSEQ(); 
		Date date = DateUtils.convertTimestampToDate(dateStr);
		
		List<CardioObj.CardioList> cardioList = JsonUtils.parseCardioListElements(cardioListStr);
		int totalTime = 0; 
		
		for(CardioObj.CardioList cardio: cardioList) {
			
			totalTime+=cardio.getTime();
		}
		
		CardioObj cardioObj = new CardioObj();
		cardioObj.setTotalTime(totalTime);
		cardioObj.setCardioList(cardioList);
		
		
		// 서비스에 전달할 DTO 생성 
		RecordDTO record = new RecordDTO();
		record.setMemberSEQ(memberSEQ);
		record.setDate(date);
		record.setCardioObj(cardioObj);
		
		return recordService.createCardio(record, member);
	}	

	@RequestMapping(value = "/createFitness.do",method = RequestMethod.POST) 
	@ResponseBody
	public int createFitness(HttpSession session, HttpServletRequest request){ 
		// 클라이언트로부터 받은 데이터
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		String dateStr = request.getParameter("date"); 		
		String fitnessListStr = request.getParameter("fitnessList"); 
		int totalTime = Integer.parseInt(request.getParameter("totalTime"));
		
		// 데이터 가공
		int memberSEQ = member.getMemberSEQ(); 
		Date date = DateUtils.convertTimestampToDate(dateStr);
		
		List<FitnessObj.FitnessList> fitnessList = JsonUtils.parseFitnessListElements(fitnessListStr);
		
				
		FitnessObj fitnessObj = new FitnessObj();
		fitnessObj.setTotalTime(totalTime);
		fitnessObj.setFitnessList(fitnessList);
		
		// 서비스에 전달할 DTO 생성 
		RecordDTO record = new RecordDTO();
		record.setMemberSEQ(memberSEQ);
		record.setDate(date);
		record.setFitnessObj(fitnessObj);
		
		return recordService.createFitness(record, member);
	}	

	@RequestMapping(value = "/createFood.do",method = RequestMethod.POST) 
	@ResponseBody
	public int createFood(HttpSession session, HttpServletRequest request){ 
		// 클라이언트로부터 받은 데이터
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		String dateStr = request.getParameter("date"); 		
		String foodListStr = request.getParameter("foodList"); 
		
		// 데이터 가공
		int memberSEQ = member.getMemberSEQ(); 
		Date date = DateUtils.convertTimestampToDate(dateStr);
		
		List<FoodObj> foodObj = JsonUtils.parseFoodObjList(foodListStr);
		
		// 서비스에 전달할 DTO 생성 
		RecordDTO record = new RecordDTO();
		record.setMemberSEQ(memberSEQ);
		record.setDate(date);
		record.setFoodObj(foodObj);
		
		return recordService.createFood(record);
	}	

	@RequestMapping(value = "/createStatus.do",method = RequestMethod.POST) 
	@ResponseBody
	public int createStatus(HttpSession session, HttpServletRequest request){ 
		// 클라이언트로부터 받은 데이터
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		String dateStr = request.getParameter("date"); 		
		String statusStr = request.getParameter("status"); 
		
		// 데이터 가공
		int memberSEQ = member.getMemberSEQ(); 
		Date date = DateUtils.convertTimestampToDate(dateStr);
		int status = Integer.parseInt(statusStr);
		
		// 서비스에 전달할 DTO 생성 
		RecordDTO record = new RecordDTO();
		record.setMemberSEQ(memberSEQ);
		record.setDate(date);
		record.setStatus(status);
		
		return recordService.createStatus(record);
	}	
	
	@RequestMapping(value = "/updateCardio.do",method = RequestMethod.POST) 
	@ResponseBody
	public int updateCardio(HttpSession session, HttpServletRequest request){ 
		// 클라이언트로부터 받은 데이터
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		String dateStr = request.getParameter("date"); 		
		String cardioListStr = request.getParameter("cardioList");
		
		// 데이터 가공
		int memberSEQ = member.getMemberSEQ(); 
		Date date = DateUtils.convertTimestampToDate(dateStr);
		
		List<CardioObj.CardioList> cardioList = JsonUtils.parseCardioListElements(cardioListStr);
		int totalTime = 0; 
		
		for(CardioObj.CardioList cardio: cardioList) {
			
			totalTime+=cardio.getTime();
		}
		
		CardioObj cardioObj = new CardioObj();
		cardioObj.setTotalTime(totalTime);
		cardioObj.setCardioList(cardioList);
		
		
		// 서비스에 전달할 DTO 생성 
		RecordDTO record = new RecordDTO();
		record.setMemberSEQ(memberSEQ);
		record.setDate(date);
		record.setCardioObj(cardioObj);
		
		return recordService.updateCardio(record, member);
	}	

	@RequestMapping(value = "/updateFitness.do",method = RequestMethod.POST) 
	@ResponseBody
	public int updateFitness(HttpSession session, HttpServletRequest request){ 
		
		// 클라이언트로부터 받은 데이터
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		String dateStr = request.getParameter("date"); 		
		String fitnessListStr = request.getParameter("fitnessList"); 
		int totalTime = Integer.parseInt(request.getParameter("totalTime"));
		
		// 데이터 가공
		int memberSEQ = member.getMemberSEQ(); 
		Date date = DateUtils.convertTimestampToDate(dateStr);
		
		List<FitnessObj.FitnessList> fitnessList = JsonUtils.parseFitnessListElements(fitnessListStr);
		FitnessObj fitnessObj = new FitnessObj();
		fitnessObj.setFitnessList(fitnessList);
		fitnessObj.setTotalTime(totalTime);
		
		// 서비스에 전달할 DTO 생성 
		RecordDTO record = new RecordDTO();
		record.setMemberSEQ(memberSEQ);
		record.setDate(date);
		record.setFitnessObj(fitnessObj);
		
		return recordService.updateFitness(record, member);
	}	

	@RequestMapping(value = "/updateFood.do",method = RequestMethod.POST) 
	@ResponseBody
	public int updateFood(HttpSession session, HttpServletRequest request){ 
		
		// 클라이언트로부터 받은 데이터
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		String dateStr = request.getParameter("date"); 		
		String foodListStr = request.getParameter("foodList"); 
		
		// 데이터 가공
		int memberSEQ = member.getMemberSEQ(); 
		Date date = DateUtils.convertTimestampToDate(dateStr);
		
		List<FoodObj> foodObjList = JsonUtils.parseFoodObjList(foodListStr);
		
		// 서비스에 전달할 DTO 생성 
		RecordDTO record = new RecordDTO();
		record.setMemberSEQ(memberSEQ);
		record.setDate(date);
		record.setFoodObj(foodObjList);
		
		return recordService.updateFood(record);
	}	

	@RequestMapping(value = "/updateStatus.do",method = RequestMethod.POST) 
	@ResponseBody
	public int updateStatus(HttpSession session, HttpServletRequest request){ 
		
		// 클라이언트로부터 받은 데이터
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		String dateStr = request.getParameter("date"); 		
		String statusStr = request.getParameter("status"); 
		
		// 데이터 가공
		int memberSEQ = member.getMemberSEQ(); 
		Date date = DateUtils.convertTimestampToDate(dateStr);
		int status = Integer.parseInt(statusStr);
		
		// 서비스에 전달할 DTO 생성 
		RecordDTO record = new RecordDTO();
		record.setMemberSEQ(memberSEQ);
		record.setDate(date);
		record.setStatus(status);
		
		return recordService.updateStatus(record);
	}
	
	
	
	// 하나의 기록 (유산소, 무산소, 식단, 상태 모두) 삭제. index-script.js의 deleteAllEvent에 대응.
	@RequestMapping(value = "/deleteDailyRecord.do",method = RequestMethod.POST) 
	@ResponseBody
	public int deleteDailyRecord(HttpSession session, HttpServletRequest request){ 
		
		// 클라이언트로부터 받은 데이터
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		String dateStr = request.getParameter("date"); 		
		
		// 데이터 가공
		int memberSEQ = member.getMemberSEQ(); 
		Date date = DateUtils.convertTimestampToDate(dateStr);
		
		// 서비스에 전달할 DTO 생성 
		RecordDTO record = new RecordDTO();
		record.setMemberSEQ(memberSEQ);
		record.setDate(date);
		
		return recordService.deleteDailyRecord(record, member);
	}	

	// 하나의 이벤트 (유산소, 무산소, 식단, 상태 중 1) 삭제. index-script.js의 deleteEvent에 대응.
	@RequestMapping(value = "/deleteField.do",method = RequestMethod.POST) 
	@ResponseBody
	public int deleteField(HttpSession session, HttpServletRequest request){ 
		
		// 클라이언트로부터 받은 데이터
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		String dateStr = request.getParameter("date"); 		
		String field = request.getParameter("field"); 
		
		// 데이터 가공
		int memberSEQ = member.getMemberSEQ(); 
		Date date = DateUtils.convertTimestampToDate(dateStr);
		
		// 서비스에 전달할 DTO 생성 
		RecordDTO record = new RecordDTO();
		record.setMemberSEQ(memberSEQ);
		record.setDate(date);
		
		return recordService.deleteField(record, field, member);
	}	

	// 모든 유산소 운동(cardio) 조회
	@RequestMapping(value = "/findAllCardio.do",method = RequestMethod.GET) 
	@ResponseBody
	public List<CardioDTO> findAllCardio(){ 
		
		return recordService.findAllCardio();
	}	

	// 모든 무산소 운동(fitness) 조회
	@RequestMapping(value = "/findAllFitness.do",method = RequestMethod.GET) 
	@ResponseBody
	public List<FitnessDTO> findAllFitness(){ 
		
		return recordService.findAllFitness();
	}	

}
