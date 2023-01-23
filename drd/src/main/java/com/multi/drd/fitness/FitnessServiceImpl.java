package com.multi.drd.fitness;

import java.util.List;
import java.util.Map;

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
	@Override
	public List<FitnessDTO> findbymusclegroup(String musclegroup) {
		// TODO Auto-generated method stub
		return dao.findbymusclegroup(musclegroup);
	}
	@Override
	public List<FitnessDTO> findbymusclegroupwithseq(Map<String, Object> map) {
		return dao.findbymusclegroupwithSEQ(map);
	}
	@Override
	public List<FitnessDTO> findbyname(String name) {
		return dao.findbyname(name);
	}

}
