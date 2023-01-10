package com.multi.drd.record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value = "/test",method = RequestMethod.GET) 
	List<RecordDTO> findFoodList(){ 
		return recordService.findFoodList(2); // 일단 memberSEQ=2 인 멤버로 테스트
	}

	@RequestMapping(value = "/findLastRecord",method = RequestMethod.GET) 
	String findLastRecord(){ 
		
		RecordDTO latestRecord = recordService.latestRecord(1); 
		
		System.out.println("latestRecord: "+latestRecord);
		System.out.println("cardioList: "+latestRecord.getCardioList());
		System.out.println("fitnessList: "+latestRecord.getFitnessList());
		
		return "some/dummy";
	}
	
	

}
