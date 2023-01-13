package com.multi.drd.goal;

public interface GoalDAO {
	GoalDTO readGoal(int memberSEQ);
	int updateTime(GoalDTO goal);
	int updateCalory(GoalDTO goal);
}
