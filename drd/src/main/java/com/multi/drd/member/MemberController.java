package com.multi.drd.member;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.multi.drd.routine.RoutineDTO;

/*
 * @SessionAttributes("member") 
 *  - member: attribute 이름
 *  - 컨트롤러에서 member라는 이름으로 Model 객체에 저장된 attribute가 있다면 이를 세션에 저장. login 관련 메소드 참조.
 */

@Controller  
@RequestMapping("member")
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
			  viewName = "redirect: /personalroutine/setpage"; 
		  } 
		  else {
			  viewName = "member/login"; 
		  }  
		  
		  return viewName;
	} 
	
	@RequestMapping(value = "/register.do",method = RequestMethod.GET)
	public String registerPage() {
		return "member/register";
	} 
	
	@RequestMapping(value = "/register.do",method = RequestMethod.POST)
	public String register(MemberDTO registerMember, MemberBioDTO registerMemberBio, Model model, HttpServletRequest request) {
				
		
		int registerSEQ = memberService.register(registerMember); 
		String viewName = ""; 
		
		System.out.println("selectedRoutine: " + request.getParameter("selectedRoutine"));
		
		
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
		 
		  return viewName; 
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
	/*
	 * Member의 기본키를 통해 MemberBio를 가져오는 기능 예시
	 */
	@RequestMapping(value = "/biotest.do", method = RequestMethod.GET)
	public String bioTest(HttpSession session) {
		
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		
		return "member/index";
	} 

	/*
	 * 회원 가입 시 추천 루틴 가져오기 . 
	 * 인송님께 말하기
	 * 230112 현재 질병 정보만을 기반으로 루틴을 추천하지만, 추후 확장을 위해 param 변수 생성
	 * 
	 */
	@RequestMapping(value = "/findRoutineByRegisterInfo.do", method = RequestMethod.POST) 
	@ResponseBody
	public List<RoutineDTO> findByRegisterInfo(HttpServletRequest request) {
		
		HashMap<String, Object> param = new HashMap<String, Object>();
	    
	    Enumeration<String> enumber = request.getParameterNames();
	    
	    while (enumber.hasMoreElements()) {
	        String key = enumber.nextElement().toString();
	        String value = request.getParameter(key);

	        param.put(key, value);  
	    }  
		List<RoutineDTO> routineList = memberService.findRoutineByRegisterInfo(param);
	
		return routineList;
	} 
	
	
	
}
