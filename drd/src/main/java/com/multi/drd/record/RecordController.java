package com.multi.drd.record;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.drd.member.MemberDTO;

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
	RecordDTO findLatestRecord(){ 
		
		RecordDTO latestRecord = recordService.findLatestRecord(1); 
	
		return latestRecord;
	}
	 
	/* 사용자가 오늘 작성한 기록을 조회.
	 * 없을 경우 null을 반환
	 */
	@RequestMapping(value = "/findTodayRecord.do",method = RequestMethod.GET) 
	@ResponseBody
	RecordDTO findTodayRecord(HttpSession session){  
		RecordDTO result = recordService.findTodayRecord(1); //memberSEQ가 들어가야 하지만, 테스트 상 1로 함.
		System.out.println("result: " + result);
		return result;
	}	

	@RequestMapping(value = "/findMonthlyRecord.do",method = RequestMethod.GET) 
	@ResponseBody
	List<RecordDTO> findMonthlyRecord(HttpSession session){ 
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		List<RecordDTO> result = recordService.findMonthlyRecord(member.getMemberSEQ(),2023,1); //memberSEQ가 들어가야 하지만, 테스트 상 1로 함.
		return result;
	}	

	@RequestMapping(value = "/index.do",method = RequestMethod.GET) 
	String indexPage(){ 
		
		return "record/index";
	}	

}
