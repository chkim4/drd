package com.multi.drd.routine;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.drd.member.MemberDTO;

@Service
public class RoutineServiceImpl implements RoutineService {
	
	private RoutineDAO dao; 
	
	public RoutineServiceImpl() {
		super();
	} 
	
	@Autowired
	public RoutineServiceImpl(RoutineDAO dao) {
		super();
		this.dao = dao;
	}  
	
	@Override
	public List<RoutineDTO> findAll() {
		return dao.findAll();
	}

	@Override
	public List<RoutineDTO> findByRegisterInfo(HashMap<String,Object> param) {
		return dao.findByRegisterInfo(param);
	}
	
	

}
