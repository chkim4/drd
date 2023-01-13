package com.multi.drd.fitness;

public class FitnessDTO {
String name;
String muscleGroup;
String equipment;
public FitnessDTO() {}
public FitnessDTO(String name, String muscleGroup, String equipment) {
	super();
	this.name = name;
	this.muscleGroup = muscleGroup;
	this.equipment = equipment;
}
@Override
public String toString() {
	return "FitnessDTO [name=" + name + ", muscleGroup=" + muscleGroup + ", equipment=" + equipment + "]";
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
