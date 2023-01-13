package com.multi.drd.goal;

public class GoalDTO {
	private int goalSEQ;
	private int memberSEQ;
	private int goalExerciseTime;
	private int goalCalory;
	private int goalProtein;
	
	public GoalDTO() {}
	
	public GoalDTO(int goalSEQ, int memberSEQ, int goalExerciseTime, int goalCalory, int goalProtein) {
		super();
		this.goalSEQ = goalSEQ;
		this.memberSEQ = memberSEQ;
		this.goalExerciseTime = goalExerciseTime;
		this.goalCalory = goalCalory;
		this.goalProtein = goalProtein;
	}

	public int getGoalSEQ() {
		return goalSEQ;
	}

	public void setGoalSEQ(int goalSEQ) {
		this.goalSEQ = goalSEQ;
	}

	public int getMemberSEQ() {
		return memberSEQ;
	}

	public void setMemberSEQ(int memberSEQ) {
		this.memberSEQ = memberSEQ;
	}

	public int getGoalExerciseTime() {
		return goalExerciseTime;
	}

	public void setGoalExerciseTime(int goalExerciseTime) {
		this.goalExerciseTime = goalExerciseTime;
	}

	public int getGoalCalory() {
		return goalCalory;
	}

	public void setGoalCalory(int goalCalory) {
		this.goalCalory = goalCalory;
	}

	public int getGoalProtein() {
		return goalProtein;
	}

	public void setGoalProtein(int goalProtein) {
		this.goalProtein = goalProtein;
	}

	@Override
	public String toString() {
		return "GoalDTO [goalSEQ=" + goalSEQ + ", memberSEQ=" + memberSEQ + ", goalExerciseTime=" + goalExerciseTime
				+ ", goalCalory=" + goalCalory + ", goalProtein=" + goalProtein + "]";
	}
	
	
}
