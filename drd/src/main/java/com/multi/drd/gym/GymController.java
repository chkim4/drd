package com.multi.drd.gym;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	//등록하려는 헬스장 정보
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerGym(@RequestBody GymDTO dto, HttpSession session, Model model, HttpServletRequest request) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");

		String name = dto.getName();
		String address = dto.getAddress();
		String telephoneNumber = dto.getTelephoneNumber();
		
		
		GymDTO gym = new GymDTO(name, address, telephoneNumber);
		
		GymDTO gymExists = gymService.findByGymName(name);
		
		if(gymExists != null) {//이미 gym테이블에 데이터가 있다면 member의 gymSEQ update
			HashMap<String, Integer> param = new HashMap<String, Integer>();
			param.put("gymSEQ", gymExists.getGymSEQ());
			param.put("memberSEQ",member.getMemberSEQ());
			System.out.println(param);
			gymService.updateGym(param);
		} else {//없다면 gym테이블에 insert후 update
			gymService.insertGym(gym);
			HashMap<String, Integer> param = new HashMap<String, Integer>();
			param.put("gymSEQ", gym.getGymSEQ());
			param.put("memberSEQ",member.getMemberSEQ());
			System.out.println("insert param" + param);
			gymService.updateGym(param);
			
		}
		
		
		
		return "redirect:/dashboard/read";
	}
	
	
	

}
