package com.multi.drd.member;

import java.util.HashMap;
import java.util.List;

import com.multi.drd.routine.RoutineDTO;

public interface MemberService {
	
	// 회원가입 
	int register(MemberDTO registerMember);
	
	// 로그인 
	MemberDTO login(MemberDTO loginMember);
	
	//닉네임으로 검색 
	MemberDTO findByNickName(String nickName);
	
	//이메일로 검색 
	MemberDTO findByEmail(String email); 
	
	//가입 전 추천 루틴 정보 조회. 인송님께 말하기
	List<RoutineDTO> findRoutineByRegisterInfo(HashMap<String,Object> param);
}
