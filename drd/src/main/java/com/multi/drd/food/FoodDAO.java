package com.multi.drd.food;

import java.util.List;

public interface FoodDAO { 
	
	public List<FoodDTO> findAll();
	
	public List<FoodDTO> findFoodListByPK(List<String> foodSEQList);
}
