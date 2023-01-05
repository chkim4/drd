package com.multi.drd.routine;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller 
@RequestMapping("/routine")
public class RoutineController { 
	private RoutineService routineService;

	public RoutineController() {
		super();
	}
	
	@Autowired
	public RoutineController(RoutineService routineService) {
		super();
		this.routineService = routineService;
	}  
	
	// 전체 루틴 조회 
	@RequestMapping(value = "/findAll.do", method = RequestMethod.POST)
	@ResponseBody
	public List<RoutineDTO> findAll() {
		return routineService.findAll();
	}
	
	// 회원 가입 시 기입한 정보를 토대로 루틴 정보 호출
	@RequestMapping(value = "/findByRegisterInfo.do",method = RequestMethod.POST)
	@ResponseBody
	public List<RoutineDTO> findByRegisterInfo(HttpServletRequest request) {
		HashMap<String, Object> param = new HashMap<String, Object>();
	    
	    Enumeration<String> enumber = request.getParameterNames();
	    
	    while (enumber.hasMoreElements()) {
	        String key = enumber.nextElement().toString();
	        String value = request.getParameter(key);

	        param.put(key, value);  
	    } 
		List<RoutineDTO> routineList = routineService.findByRegisterInfo(param); 
		
		return routineList;
	} 
	
	
}
