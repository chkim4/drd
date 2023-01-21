package com.multi.drd.food;

import java.util.List;

public interface FoodDAO {
	List<FoodDTO> findFoodListByPK(List<String> foodSEQList);
}
