package com.multi.drd.goal;

import java.util.List;
import java.util.Map;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.record.RecordDTO;

public interface GoalService {
	GoalDTO readGoal(int memberSEQ);
	int updateTime(GoalDTO goal);
	int updateCalory(GoalDTO goal);
	
	List<MemberDTO> getBodyShapeList();
	int updateBodyShape(MemberDTO member);
	
	List<RecordDTO> findByWeek(int memberSEQ);
	int updateProtein(Map<String, Integer> amountAndSeq);
	
	MemberDTO findByMemberSeq(String memberSEQ);

}
