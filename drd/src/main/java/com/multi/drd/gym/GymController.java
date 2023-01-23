package com.multi.drd.gym;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.drd.member.MemberDTO;

@Controller
@RequestMapping("/gym")
public class GymController {
	GymService gymService;

	public GymController() {
		super();
	}
	
	@Autowired
	public GymController(GymService gymService) {
		super();
		this.gymService = gymService;
	}
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerGym(@RequestBody GymDTO dto, HttpSession session, Model model, HttpServletRequest request) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		/*
		 * String name = request.getParameter("name"); String address =
		 * request.getParameter("address"); String telephoneNumber =
		 * request.getParameter("telephoneNumber");
		 */
		String name = dto.getName();
		String address = dto.getAddress();
		String telephoneNumber = dto.getTelephoneNumber();
		
		GymDTO gym = new GymDTO(name, address, telephoneNumber, member.getMemberSEQ());
		
		if(gymService.readGym(member.getMemberSEQ()) != null) {
			gymService.updateGym(gym);
		} else {//값이 없다면 등록
			gymService.registerGym(gym);
		}
		
		System.out.println(gym);
		
		return "redirect: /dashboard/dashboard";
	}
	
	
	

}
