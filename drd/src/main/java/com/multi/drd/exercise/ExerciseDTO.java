package com.multi.drd.exercise;

public class ExerciseDTO {
	private int exerciseSEQ;
	private String name;
	private String muscleGroup;
	private String equipment;
	private String pushpull;
	public ExerciseDTO() {}
	public ExerciseDTO(int exerciseSEQ, String name, String muscleGroup, String equipment, String pushpull) {
		super();
		this.exerciseSEQ = exerciseSEQ;
		this.name = name;
		this.muscleGroup = muscleGroup;
		this.equipment = equipment;
		this.pushpull = pushpull;
	}
	
	@Override
	public String toString() {
		return "ExerciseDTO [exerciseSEQ=" + exerciseSEQ + ", name=" + name + ", muscleGroup=" + muscleGroup
				+ ", equipment=" + equipment + ", pushpull=" + pushpull + "]";
	}
	public int getExerciseSEQ() {
		return exerciseSEQ;
	}
	public void setExerciseSEQ(int exerciseSEQ) {
		this.exerciseSEQ = exerciseSEQ;
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
	public String getPushpull() {
		return pushpull;
	}
	public void setPushpull(String pushpull) {
		this.pushpull = pushpull;
	}
	
}
