package com.multi.drd.routine;

import java.util.HashMap;
import java.util.List;

public interface RoutineDAO { 
		
	List<RoutineDTO> findAll();

	List<RoutineDTO> findByNoInfo();
	
	List<RoutineDTO> findByRegisterInfo(HashMap<String,Object> param);
}
