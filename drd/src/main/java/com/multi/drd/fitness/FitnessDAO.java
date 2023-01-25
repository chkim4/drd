package com.multi.drd.fitness;

import java.util.List;
import java.util.Map;

public interface FitnessDAO {
	FitnessDTO findOne(int fitnessSEQ);
	List<FitnessDTO> findbymusclegroup(String musclegroup);
	List<FitnessDTO> findbymusclegroupwithSEQ(Map<String, Object> map);
	List<FitnessDTO> findbyname(String name);
	List<FitnessDTO> findbynamewithseq(Map<String, Object> map);
}
