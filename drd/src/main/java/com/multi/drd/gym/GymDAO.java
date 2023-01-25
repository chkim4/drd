package com.multi.drd.gym;

import java.util.HashMap;

public interface GymDAO {

int insertGym(GymDTO gym);

GymDTO findByGymName(String name);

int updateGym(HashMap<String, Integer> param);

GymDTO readGym(int memberSEQ);

int deleteGym(int memberSEQ);
	
	

}
