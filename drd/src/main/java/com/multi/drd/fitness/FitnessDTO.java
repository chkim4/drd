package com.multi.drd.fitness;

public class FitnessDTO {
	private int fitnessSEQ;
	private String name;
	private String muscleGroup;
	private String equipment;
	public FitnessDTO() {}
	public FitnessDTO(int fitnessSEQ, String name, String muscleGroup, String equipment) {
		super();
		this.fitnessSEQ = fitnessSEQ;
		this.name = name;
		this.muscleGroup = muscleGroup;
		this.equipment = equipment;
	}
	@Override
	public String toString() {
		return "FitnessDTO [fitnessSEQ=" + fitnessSEQ + ", name=" + name + ", muscleGroup=" + muscleGroup + ", equipment="
				+ equipment + "]";
	}
	public int getFitnessSEQ() {
		return fitnessSEQ;
	}
	public void setFitnessSEQ(int fitnessSEQ) {
		this.fitnessSEQ = fitnessSEQ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMuscleGroup() {
		return muscleGroup;
	}
	public void setMuscleGroup(String muscleGroup) {
		this.muscleGroup = muscleGroup;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

}
