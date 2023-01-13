package com.multi.drd.fitness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FitnessServiceImpl implements FitnessService {
	private FitnessDAO dao;
	public FitnessServiceImpl() {}
	@Autowired
	public FitnessServiceImpl(FitnessDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public FitnessDTO findOne(int fitnessSEQ) {
		return dao.findOne(fitnessSEQ);
	}

}
