package com.multi.drd.goal;

public interface GoalService {
	public GoalDTO readGoal(int memberSEQ);
	public int updateTime(GoalDTO goal);
	public int updateCalory(GoalDTO goal);
}
