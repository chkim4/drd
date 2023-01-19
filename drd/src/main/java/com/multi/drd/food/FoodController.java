package com.multi.drd.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}
