package com.multi.drd.goal;

import java.util.List;
import java.util.Map;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.record.RecordDTO;

public interface GoalDAO {
	GoalDTO readGoal(int memberSEQ);
	int updateTime(GoalDTO goal);
	int updateCalory(GoalDTO goal);
	
	public List<MemberDTO> getBodyShapeList();
	public int updateBodyShape(MemberDTO member);
	
	public List<RecordDTO> findByWeek(int memberSEQ);   
	public int updateProtein(Map<String, Integer> amountAndSeq);
}
