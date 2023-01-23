package com.multi.drd.food;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping("food")
public class FoodController {
	
	FoodService service;

	public FoodController() {
		super();
	}
	
	@Autowired
	public FoodController(FoodService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping(value = "/findAll.do",method = RequestMethod.GET) 
	@ResponseBody
	public List<FoodDTO> findAll(){
		return service.findAll();
	}
	
}
