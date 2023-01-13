package com.multi.drd.personalroutine;

public interface PersonalRoutineDAO {
	PersonalRoutineDTO findOne();
	PersonalRoutineDTO findOne1(int PersonalRoutineSEQ);
	int updateOne(PersonalRoutineDTO pr);
}
