package com.multi.drd.goal;

import java.util.List;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.record.RecordDTO;

public interface GoalService {
	public GoalDTO readGoal(int memberSEQ);
	public int updateTime(GoalDTO goal);
	public int updateCalory(GoalDTO goal);
	
	public List<MemberDTO> getBodyShapeList();
	public int updateBodyShape(MemberDTO member);
	
	public List<RecordDTO> findByWeek(int memberSEQ);
}
