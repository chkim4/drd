package com.multi.drd.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.drd.member.MemberDTO;

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
	
	
	
}
