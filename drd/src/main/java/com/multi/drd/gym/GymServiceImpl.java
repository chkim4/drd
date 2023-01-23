package com.multi.drd.gym;

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
		// TODO Auto-generated method stub
		return dao.readGym(memberSEQ);
	}

	@Override
	public int updateGym(GymDTO gym) {
		// TODO Auto-generated method stub
		return dao.updateGym(gym);
	}

	@Override
	public int deleteGym(int memberSEQ) {
		// TODO Auto-generated method stub
		return dao.deleteGym(memberSEQ);
	}

	@Override
	public int registerGym(GymDTO gym) {
		// TODO Auto-generated method stub
		return dao.registerGym(gym);
	}

}
