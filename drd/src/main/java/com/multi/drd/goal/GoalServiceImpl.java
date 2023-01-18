package com.multi.drd.goal;

import java.util.Map;

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

	@Override
	public int updateProtein(Map<String, Integer> amountAndSeq) {
		// TODO Auto-generated method stub
		return dao.updateProtein(amountAndSeq);
	}
//	@Override
//	public int updateProtein(GoalDTO goal) {
//		// TODO Auto-generated method stub
//		return dao.updateProtein(goal);
//	}

	
}
