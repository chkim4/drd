package com.multi.drd.routine;

import java.util.HashMap;
import java.util.List;

public interface RoutineDAO { 
		
	List<RoutineDTO> findAll();

	List<RoutineDTO> findByNoInfo();
	
	RoutineDTO findBySEQ(int RoutineSEQ);
	
	List<RoutineDTO> findByRegisterInfo(HashMap<String,Object> param);
}
