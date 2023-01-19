package com.multi.drd.goal;

import java.util.List;
import java.util.Map;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.record.RecordDTO;

public interface GoalDAO {
	GoalDTO readGoal(int memberSEQ);
	
	int updateTime(GoalDTO goal);
	
	int updateCalory(GoalDTO goal);
	
	int updateProtein(Map<String, Integer> amountAndSeq);
	
	List<RecordDTO> findByWeek(int memberSEQ);   

	
	List<MemberDTO> getBodyShapeList();
	
	int updateBodyShape(MemberDTO member);
	
	MemberDTO findByMemberSeq(String memberSEQ); 

	
}
