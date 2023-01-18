package com.multi.drd.personalroutine;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalRoutineServiceImpl implements PersonalRoutineService {
	
	private PersonalRoutineDAO dao;
	

	public PersonalRoutineServiceImpl() {
		super();
	}
	
	@Autowired
	public PersonalRoutineServiceImpl(PersonalRoutineDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public PersonalRoutineDTO findOne() {
		return dao.findOne();
	}  
	public PersonalRoutineDTO findOne1(int PersonalRoutineSEQ) {
		return dao.findOne1(PersonalRoutineSEQ);
	}  	
	@Override
	public int updateOne(PersonalRoutineDTO pr) {
		return dao.updateOne(pr);
	}

	@Override
	public void updatefitness(Map<String, Object> map) {
		dao.updatefitness(map);
	}

	@Override
	public void updatecardio(Map<String, Object> map) {
		dao.updatecardio(map);
	}

}
