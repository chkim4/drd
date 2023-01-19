package com.multi.drd.goal;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.record.RecordDTO;

@Service
public class GoalServiceImpl implements GoalService {
	GoalDAO dao;
	
	public GoalServiceImpl () {}
	
	@Autowired
	public GoalServiceImpl(GoalDAO dao) {
		super();
		this.dao = dao;
	}
	@Override
	public GoalDTO readGoal(int memberSEQ) {
		return dao.readGoal(memberSEQ);
	}
	@Override
	public int updateTime(GoalDTO goal) {
		return dao.updateTime(goal);
	}
	@Override
	public int updateCalory(GoalDTO goal) {
		return dao.updateCalory(goal);
	}

	@Override
	public List<MemberDTO> getBodyShapeList() {
		return dao.getBodyShapeList();
	}

	@Override
	public int updateBodyShape(MemberDTO member) {
		return dao.updateBodyShape(member);
	}
	
	public List<RecordDTO> findByWeek(int memberSEQ){
		return dao.findByWeek(memberSEQ);
	}

	@Override
	public int updateProtein(Map<String, Integer> amountAndSeq) {
		return dao.updateProtein(amountAndSeq);
	}

	@Override
	public MemberDTO findByMemberSeq(String memberSEQ) {
		return dao.findByMemberSeq(memberSEQ);
	}
	
	
}
