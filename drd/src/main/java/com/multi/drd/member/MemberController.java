package com.multi.drd.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping(value = "/member/login.do",method = RequestMethod.POST)
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
		  
		  return viewName; 
	} 
	
	@RequestMapping(value = "/member/register.do",method = RequestMethod.GET)
	public String registerPage() {
		return "member/register";
	} 
	
	@RequestMapping(value = "/member/register.do",method = RequestMethod.POST)
	public String register(MemberDTO registerMember, MemberBioDTO registerMemberBio, Model model) {
		
		System.out.println(registerMember); 
		
		// 나이 계산
		Date current = new Date();
		Date birth = new Date(registerMember.getBirth().getTime());   
		
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy"); 
		SimpleDateFormat monthDateFormat = new SimpleDateFormat("MMdd"); 
	
		int currentYear = Integer.parseInt(yearFormat.format(current)); 
		int birthYear = Integer.parseInt(yearFormat.format(birth)); 
		
		int currentMonthDate = Integer.parseInt(monthDateFormat.format(current));
		int birthMonthDate = Integer.parseInt(monthDateFormat.format(birth));
		
		int age = currentYear - birthYear; 
		age += birthMonthDate > currentMonthDate ? -1:0; // 만나이 계산: 생일이 아직 안 지났을 경우 1을 뺀다. 
		
				
		int registerResult = memberService.register(registerMember); 
				
		registerMemberBio.setAge(age);  
		
		memberBioService.register(registerMemberBio);
		
		String viewName = ""; 
		 
		 // 회원 가입 성공 시 
		 if(registerResult > 0) { 
			  model.addAttribute("member", registerMember); 
			  viewName = "member/index"; 
		  } 
		  else {
			  viewName = "member/register"; 
		  }
		 
		  return viewName; 
	}
	
	@RequestMapping(value = "/member/login.do",method = RequestMethod.GET)
	public String loginPage() {
		return "member/login";
	} 
	
	// 닉네임으로 사용자 검색. (회원 가입 시 사용 가능 여부 체크 등에 활용)
	@RequestMapping(value = "/member/findByNickName.do", method = RequestMethod.POST) 
	@ResponseBody
	public MemberDTO findByNickName(String nickName) {
		return memberService.findByNickName(nickName);
	} 
	
	// 이메일로 사용자 검색. (회원 가입 시 사용 가능 여부 체크 등에 활용)
	@RequestMapping(value = "/member/findByEmail.do", method = RequestMethod.POST) 
	@ResponseBody
	public MemberDTO findByEmail(String email) {
		return memberService.findByEmail(email);
	}
}
