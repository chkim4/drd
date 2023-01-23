package com.multi.drd.cardio;

import java.util.List;
import java.util.Map;

public interface CardioDAO {
	CardioDTO findOne(int cardioSEQ);
	List<CardioDTO> findByIntensity(int intensity);
	List<CardioDTO> findByIntensitywithseq(Map<String, Object> map);
	List<CardioDTO> findbyname(String name);
}
