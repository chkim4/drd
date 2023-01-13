package com.multi.drd.member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.drd.routine.RoutineDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	MemberDAO dao;  
	
	public MemberServiceImpl() {
		super();
	}

	@Autowired
	public MemberServiceImpl(MemberDAO dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	public int register(MemberDTO registerMember) {
		
		return dao.register(registerMember);
	}
	
	@Override
	public MemberDTO login(MemberDTO loginMember) {
		
		return dao.login(loginMember);
	} 
	
	@Override
	public MemberDTO findByNickName(String nickName) {
		return dao.findByNickName(nickName);
	}

	@Override
	public MemberDTO findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public List<RoutineDTO> findRoutineByRegisterInfo(HashMap<String, Object> param) {
		return dao.findRoutineByRegisterInfo(param);
	} 
	
	

}
