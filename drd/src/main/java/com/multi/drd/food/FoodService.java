package com.multi.drd.food;

import java.util.List;

public interface FoodService {
	
	// SQL의 In 연산자를 이용하여 조회
	List<FoodDTO> findFoodListByPK(List<Integer> foodSEQList);

}
