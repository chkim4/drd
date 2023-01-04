package com.multi.drd.routine;

public class RoutineDTO {
	private int routineSEQ;
	private String name; 
	private int minAge;
	private int maxAge;
	private int minBMI;
	private int maxBMI;
	private int gender;
	public int getRoutineSEQ() {
		return routineSEQ;
	}
	public void setRoutineSEQ(int routineSEQ) {
		this.routineSEQ = routineSEQ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMinAge() {
		return minAge;
	}
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	public int getMinBMI() {
		return minBMI;
	}
	public void setMinBMI(int minBMI) {
		this.minBMI = minBMI;
	}
	public int getMaxBMI() {
		return maxBMI;
	}
	public void setMaxBMI(int maxBMI) {
		this.maxBMI = maxBMI;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "RoutineDTO [routineSEQ=" + routineSEQ + ", name=" + name + ", minAge=" + minAge + ", maxAge=" + maxAge
				+ ", minBMI=" + minBMI + ", maxBMI=" + maxBMI + ", gender=" + gender + "]";
	} 
	
}
