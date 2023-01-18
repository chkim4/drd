package com.multi.drd.personalroutine;

import java.util.Map;

public interface PersonalRoutineDAO {
	public PersonalRoutineDTO findOne();
	public PersonalRoutineDTO findOne1(int PersonalRoutineSEQ);
	public int updateOne(PersonalRoutineDTO pr);
	public void updatefitness(Map<String, Object> map);
	public void updatecardio(Map<String, Object> map);
}
