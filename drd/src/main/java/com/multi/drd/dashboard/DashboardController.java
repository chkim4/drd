package com.multi.drd.dashboard;


import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
import com.multi.drd.memberbio.MemberBioService;
import com.multi.drd.record.RecordDTO;
import com.multi.drd.record.RecordService;

@Controller
@RequestMapping("/dashboard")
@SessionAttributes("member")
public class DashboardController {
	DashboardService service;
	RecordService recordService;
	MemberBioService memberBioService;
	
	public DashboardController() {
		super();
	}
	
	@Autowired
	public DashboardController(DashboardService service, RecordService recordService, MemberBioService memberBioService) {
		super();
		this.service = service;
		this.recordService = recordService;
		this.memberBioService = memberBioService;
	}


	@RequestMapping("/read")
	public String read(Model model, HttpSession session) {
//		MemberDTO member = service.getMemberInfo(id); 
		MemberDTO member = (MemberDTO) session.getAttribute("member");  
		System.out.println("member from sesseion: " + member);
		
//		System.out.println(member);
		MemberBioDTO memberBio = memberBioService.findByPK(member.getMemberSEQ());
		RecordDTO latestRecord = recordService.findLatestRecord(member.getMemberSEQ());
		System.out.println(latestRecord);
		//recordService.ExcerciseHourByWeek(member.getMemberSEQ());
		
		//List<RecordDTO> excerciseHourWeekly = service.ExcerciseHourByWeek(member.getMemberSEQ());
		List<RecordDTO> excerciseHourMonthly = recordService.findMonthlyRecord(member.getMemberSEQ(), 2022, 12);
		//System.out.println("excerciseHourWeekly" +excerciseHourWeekly);
		System.out.println("excerciseHourMonthly" +excerciseHourMonthly);
		
		
		int totalExerciseHour = 0; 
		
		for(int i=0;i<excerciseHourMonthly.size();i++) {
			totalExerciseHour+= excerciseHourMonthly.get(i).getTotalExerciseTime();
		}

		
		
		
		model.addAttribute("member", member);
		//model.addAttribute("memberBio", memberBio);
		model.addAttribute("latestRecord", latestRecord);
		model.addAttribute("latestRecord", latestRecord);
		model.addAttribute("latestReexcerciseHourMonthlycord", latestRecord);
		
		return "dashboard/dashboard";
	}
	

}
