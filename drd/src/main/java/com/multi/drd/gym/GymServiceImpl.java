package com.multi.drd.gym;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GymServiceImpl implements GymService {
	GymDAO dao;

	public GymServiceImpl() {
		super();
	}

	@Autowired
	public GymServiceImpl(GymDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public GymDTO readGym(int memberSEQ) {
		// TODO Auto-generated
		return dao.readGym(memberSEQ);
	}

	@Override
	public int updateGym(HashMap<String, Integer> param) {
		// TODO Auto-generated method stub
		return dao.updateGym(param);
	}

	@Override
	public int deleteGym(int memberSEQ) {
		// TODO Auto-generated method
		return dao.deleteGym(memberSEQ);
	}

	@Override
	public int insertGym(GymDTO gym) {
		// TODO Auto-generated method stub
		return dao.insertGym(gym);
	}

	@Override
	public GymDTO findByGymName(String name) {
		// TODO Auto-generated method stub
		return dao.findByGymName(name);
	}

}
