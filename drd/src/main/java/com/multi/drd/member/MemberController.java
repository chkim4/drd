package com.multi.drd.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multi.drd.memberbio.MemberBioDTO;
import com.multi.drd.memberbio.MemberBioService;

/*
 * @SessionAttributes("member") 
 *  - member: attribute 이름
 *  - 컨트롤러에서 member라는 이름으로 Model 객체에 저장된 attribute가 있다면 이를 세션에 저장. login 관련 메소드 참조.
 */

@Controller  
@RequestMapping("/member")
@SessionAttributes("member") 
public class MemberController {
	MemberService memberService; 
	MemberBioService memberBioService; 
	
	public MemberController() {} 
	
	@Autowired
	public MemberController(MemberService memberService,MemberBioService memberBioService) {
		super();
		this.memberService = memberService;	
		this.memberBioService = memberBioService;	
	}     
	
	@RequestMapping(value = "/login.do",method = RequestMethod.GET)
	public String loginPage() {
		return "member/login";
	} 

	@RequestMapping(value = "/login.do",method = RequestMethod.POST)
	public String login(MemberDTO loginMember, Model model) {
		
		MemberDTO member = memberService.login(loginMember);
		String viewName = "";  
	  
		  // 로그인 성공 시 
		  if(member != null) { 
			  model.addAttribute("member", member); 
			  viewName = "member/index"; 
		  } 
		  else {
			  viewName = "member/login"; 
		  }  
		  
		  return "redirect: /member/sessiontest.do";
	} 
	
	@RequestMapping(value = "/register.do",method = RequestMethod.GET)
	public String registerPage() {
		return "member/register";
	} 
	
	@RequestMapping(value = "/register.do",method = RequestMethod.POST)
	public String register(MemberDTO registerMember, MemberBioDTO registerMemberBio, Model model) {
				
		
		int registerSEQ = memberService.register(registerMember); 
		String viewName = ""; 
		 
		 // 회원 가입 성공 시 
		 if(registerSEQ > 0) {  
			  //Bio 관련  등록    
			  registerMemberBio.setMemberBioSEQ(registerMember.getMemberSEQ());  
			  memberBioService.register(registerMemberBio);
			  
			  model.addAttribute("member", registerMember); 
			  viewName = "member/index"; 
		  } 
		  else {
			  viewName = "member/register"; 
		  }
		 
		  return "redirect: /member/sessiontest.do"; 
	} 
	
	// 아이디로 사용자 검색. (회원 가입 시 사용 가능 여부 체크 등에 활용)
	@RequestMapping(value = "/findById.do", method = RequestMethod.POST) 
	@ResponseBody
	public MemberDTO findById(String id) {
		return memberService.findById(id);
	} 

	// 닉네임으로 사용자 검색. (회원 가입 시 사용 가능 여부 체크 등에 활용)
	@RequestMapping(value = "/findByNickName.do", method = RequestMethod.POST) 
	@ResponseBody
	public MemberDTO findByNickName(String nickName) {
		return memberService.findByNickName(nickName);
	} 
	
	// 이메일로 사용자 검색. (회원 가입 시 사용 가능 여부 체크 등에 활용)
	@RequestMapping(value = "/findByEmail.do", method = RequestMethod.POST) 
	@ResponseBody
	public MemberDTO findByEmail(String email) {
		return memberService.findByEmail(email);
	} 
	
	
	
	
	/* 로그인 및 회원 가입 시 세션이 제대로 생성 되었는 지 확인하기 위함
	 * 확인 방법: login혹은 register 메소드(POST)의 리턴 값을 다음의 값으로 변경 후 콘솔에 출력되는 값 확인
	 * "redirect: /member/sessiontest.do"
	 */
	@RequestMapping(value = "/sessiontest.do", method = RequestMethod.GET)
	public String sessionTest(HttpSession session) {
		System.out.println("session: " + session.getAttribute("member"));
		
		return "member/index";
	}
}
