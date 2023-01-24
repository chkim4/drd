package com.multi.drd.member;

import java.util.HashMap;
import java.util.List;

import com.multi.drd.goal.GoalDTO;
import com.multi.drd.personalroutine.PersonalRoutineDTO;
import com.multi.drd.routine.RoutineDTO;

public interface MemberDAO {
	
	int register(MemberDTO registerMember);
	
	MemberDTO login(MemberDTO loginMember);  

	MemberDTO findByNickName(String nickName); 
	
	MemberDTO findByEmail(String email);  	
	
	MemberDTO findByPK(int memberSEQ);  	
		
	List<RoutineDTO> findRoutineByRegisterInfo(HashMap<String,Object> param); 
	
	int createPersonalRoutine(PersonalRoutineDTO pRoutine); 
	
	int updatePersonalRoutineSEQ(HashMap<String, Integer> param); 
	
	int createGoal(GoalDTO goal);
}
