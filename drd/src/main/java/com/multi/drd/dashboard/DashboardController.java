package com.multi.drd.dashboard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;

@Controller
@RequestMapping("/dashboard")
@SessionAttributes("member")
public class DashboardController {
	DashboardService service;
	
	public DashboardController() {
		super();
	}
	
	@Autowired
	public DashboardController(DashboardService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/read/{id}")
	public String read(@PathVariable String id, Model model) {
		MemberDTO member = service.getMemberInfo(id);
		MemberBioDTO dashboard = service.getMemberBio(id);
		System.out.println(member);
		model.addAttribute("member", member);
		model.addAttribute("bio", dashboard);
		return "sample/dashboard";
	}
	

}
