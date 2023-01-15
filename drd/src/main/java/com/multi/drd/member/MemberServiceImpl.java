package com.multi.drd.member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.drd.personalroutine.PersonalRoutineDTO;
import com.multi.drd.routine.RoutineDTO;
import com.multi.drd.utils.JsonUtils;

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
		// 기타 질병일 경우 고혈압과 동일한 루틴을 추천하기 위함.
		if(Integer.parseInt( param.get("disease").toString()) == 3) {
			param.put("disease", "2");
		}
		
		return dao.findRoutineByRegisterInfo(param);
	} 
	
	@Override
	public int createPersonalRoutine(PersonalRoutineDTO pRoutine) {
		
		return dao.createPersonalRoutine(pRoutine);
	}   

	@Override
	public int updatePersonalRoutineSEQ(int memberSEQ, int pRoutineSEQ) {
		HashMap<String, Integer> param = new HashMap<String, Integer>();
		param.put("memberSEQ", memberSEQ);
		param.put("personalRoutineSEQ", pRoutineSEQ);
		
		return dao.updatePersonalRoutineSEQ(param);
	}
	
	@Override
	public int createGoal(MemberDTO member) {
		
		return 0;
	}
	

}
