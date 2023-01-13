//package com.multi.drd.dashboard;
//
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import com.multi.drd.member.MemberDTO;
//import com.multi.drd.memberbio.MemberBioDTO;
//
//@Controller
//@RequestMapping("/dashboard")
//@SessionAttributes("member")
//public class DashboardController {
//	DashboardService service;
//	
//	public DashboardController() {
//		super();
//	}
//	
//	@Autowired
//	public DashboardController(DashboardService service) {
//		super();
//		this.service = service;
//	}
//
//
//	@RequestMapping("/read")
//	public String read(Model model, HttpSession session) {
////		MemberDTO member = service.getMemberInfo(id); 
//		MemberDTO member = (MemberDTO) session.getAttribute("member");  
//		System.out.println("member from sesseion: " + member);
//		
//		MemberBioDTO dashboard = service.getMemberBio(member.getId());
////		System.out.println(member);
//		
//		model.addAttribute("member", member);
//		model.addAttribute("bio", dashboard);
//		return "dashboard/dashboard";
//	}
//	
//
//}
