package com.multi.drd.goal;

import java.util.Map;

public interface GoalService {
	public GoalDTO readGoal(int memberSEQ);
	public int updateTime(GoalDTO goal);
	public int updateCalory(GoalDTO goal);
	//public int updateProtein(Map<Integer, Integer> amountAndSeq);
	//public int updateProtein(GoalDTO goal);
	int updateProtein(Map<String, Integer> amountAndSeq);
}
