package com.multi.drd.goal;

import java.util.Map;

public interface GoalDAO {
	GoalDTO readGoal(int memberSEQ);
	int updateTime(GoalDTO goal);
	int updateCalory(GoalDTO goal);
	//int updateProtein(GoalDTO goal);
	//기록횟수와 체중에 따른 권장 단백질 업데이트
	//int updateProtein(Map<Integer, Integer> amountAndSeq);
	//int updateProtein(int goalProtein, int memberSEQ);
	int updateProtein(Map<String, Integer> amountAndSeq);

}
