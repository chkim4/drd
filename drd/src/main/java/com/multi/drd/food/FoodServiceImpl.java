package com.multi.drd.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class FoodServiceImpl implements FoodService {
	
	FoodDAO dao; 
	
	public FoodServiceImpl() {
		super();
	} 
	
	@Autowired
	public FoodServiceImpl(FoodDAO dao) {
		super();
		this.dao = dao;
	}

	public List<FoodDTO> findFoodListByPK(List<Integer> foodSEQList){
		return dao.findFoodListByPK(foodSEQList);
	}
}
