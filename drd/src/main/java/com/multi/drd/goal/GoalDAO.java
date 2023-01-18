package com.multi.drd.goal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.record.RecordDTO;
import com.multi.drd.utils.DateUtils;

public interface GoalDAO {
	GoalDTO readGoal(int memberSEQ);
	int updateTime(GoalDTO goal);
	int updateCalory(GoalDTO goal);
	
	public List<MemberDTO> getBodyShapeList();
	public int updateBodyShape(MemberDTO member);
	
	public List<RecordDTO> findByWeek(int memberSEQ);   
	public int updateProtein(Map<String, Integer> amountAndSeq);   
}
