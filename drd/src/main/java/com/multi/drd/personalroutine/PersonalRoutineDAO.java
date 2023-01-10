package com.multi.drd.personalroutine;

public interface PersonalRoutineDAO {
	PersonalRoutineDTO findOne();
	int updateOne(PersonalRoutineDTO pr);
}
