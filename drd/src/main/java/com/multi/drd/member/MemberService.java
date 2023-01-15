package com.multi.drd.member;

import java.util.HashMap;
import java.util.List;

import com.multi.drd.personalroutine.PersonalRoutineDTO;
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
	
	/* 가입 후 개인 루틴 생성. 
	 * routine과 동일한 cardioObj, fintnessObj, routineSEQ 값을 지닌다. (createdAt은 null 값으로 지정) 인송님께 말하기
	 */
	int createPersonalRoutine(PersonalRoutineDTO pRoutine); 
	
	int updatePersonalRoutineSEQ(int memberSEQ, int pRoutineSEQ);

	// 가입 후 신체 정보 기반 목표 생성. 예나님께 말하기
	int createGoal(MemberDTO member);
	
}
