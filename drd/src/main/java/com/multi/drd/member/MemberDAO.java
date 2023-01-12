package com.multi.drd.member;

import java.util.HashMap;
import java.util.List;

import com.multi.drd.routine.RoutineDTO;

public interface MemberDAO {
	
	int register(MemberDTO registerMember);
	
	MemberDTO login(MemberDTO loginMember);  

	MemberDTO findByNickName(String nickName); 
	
	MemberDTO findByEmail(String email);  	
	
	//가입 전 추천 루틴 정보 조회. 인송님께 말하기
	List<RoutineDTO> findRoutineByRegisterInfo(HashMap<String,Object> param);
}
