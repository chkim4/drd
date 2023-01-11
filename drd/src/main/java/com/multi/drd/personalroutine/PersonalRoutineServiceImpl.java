package com.multi.drd.personalroutine;

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
	
	@Override
	public int updateOne(PersonalRoutineDTO pr) {
		return dao.updateOne(pr);
	}

}
