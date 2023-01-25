package com.multi.drd.mypage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.multi.drd.goal.GoalService;
import com.multi.drd.gym.GymDTO;
import com.multi.drd.gym.GymService;
import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;

@Controller
@RequestMapping("/mypage")
@SessionAttributes("member")
public class MypageController {
	MypageService service;
	GoalService goalservice;
	GymService gymservice;
	
	
	public MypageController() {}
	
	@Autowired 
	public MypageController(MypageService service, GoalService goalservice, GymService gymservice) {
		super();
		this.service = service;
		this.goalservice = goalservice;
		this.gymservice = gymservice;
	}
	
	@RequestMapping("/readAll")
	public String mypage(Model model, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		int memberSEQ = member.getMemberSEQ();
		member = goalservice.findByMemberSeq(String.valueOf(memberSEQ));
		MemberBioDTO memberbio = service.getMemberBio(memberSEQ);
		GymDTO gym = gymservice.readGym(memberSEQ); 
		
		model.addAttribute("member", member);
		model.addAttribute("memberbio",memberbio);
		model.addAttribute("gym",gym);
		return "mypage/mypage";
	}
	
	

	//회원정보 수정
	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
	@ResponseBody
	public MemberDTO updateInfo(MemberDTO member, HttpSession session) {
		int result = service.updateInfo(member);
	    System.out.println(result);
		return member;
	}
	
	//비밀번호 수정
	@RequestMapping(value="/updatePass" , method=RequestMethod.POST)
	public String updatePass(MemberDTO member, HttpSession session, RedirectAttributes ra, SessionStatus status){
		int result = service.updatePwd(member);
		session.invalidate();
		ra.addFlashAttribute("result","updateok");
		status.setComplete();
		return "redirect:/";
	}
	
	//탈퇴
	@RequestMapping(value="/deleteUser" , method=RequestMethod.POST)
	public String deleteUser(MemberDTO member, HttpSession session) {
		int result = service.deleteUser(member);
		session.invalidate();
		System.out.println(result);
		return "redirect:/";
	}
}
