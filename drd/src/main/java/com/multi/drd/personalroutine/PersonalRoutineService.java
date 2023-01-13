package com.multi.drd.personalroutine;

public interface PersonalRoutineService {
	PersonalRoutineDTO findOne();
	PersonalRoutineDTO findOne1(int personalRoutinSEQ); 
	int updateOne(PersonalRoutineDTO pr);
}
