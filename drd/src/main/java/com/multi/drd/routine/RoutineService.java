package com.multi.drd.routine;

import java.util.HashMap;
import java.util.List;

public interface RoutineService { 
	
	List<RoutineDTO> findAll();
	RoutineDTO findBySEQ(int RoutineSEQ);
	List<RoutineDTO> findByRegisterInfo(HashMap<String,Object> param);
}
