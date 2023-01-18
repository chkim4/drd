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

import com.multi.drd.json.CardioObj;
import com.multi.drd.member.MemberDTO;
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
		System.out.println("result: " + result);
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

	@RequestMapping(value = "/updateCardio.do",method = RequestMethod.POST) 
	@ResponseBody
	public int updateCardio(HttpSession session, HttpServletRequest request){ 
		
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		String date = request.getParameter("date"); 
		
		String cardioObjListStr = request.getParameter("cardioObjList");
		List<CardioObj.CardioList> cardioObjList = JsonUtils.parseCardioObjElements(cardioObjListStr);
		
		int memberSEQ = member.getMemberSEQ(); 
		System.out.println("memberSEQ: " + memberSEQ);
		System.out.println("date: " + date);
		System.out.println("cardioObjList: " + cardioObjList);
		
		
		return 1;
	}	

}
