package com.multi.drd.routine;

public class RoutineDTO {
	private int routineSEQ; 
	private String fitnessObj;
	private String cardioObj;
	private String name;
	private String description;
	private int routineStep;
	private int disease;
	private int period;
	
	public RoutineDTO() {
		super();
	}

	public int getRoutineSEQ() {
		return routineSEQ;
	}

	public void setRoutineSEQ(int routineSEQ) {
		this.routineSEQ = routineSEQ;
	}

	public String getFitnessObj() {
		return fitnessObj;
	}

	public void setFitnessObj(String fitnessObj) {
		this.fitnessObj = fitnessObj;
	}

	public String getCardioObj() {
		return cardioObj;
	}

	public void setCardioObj(String cardioObj) {
		this.cardioObj = cardioObj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRoutineStep() {
		return routineStep;
	}

	public void setRoutineStep(int routineStep) {
		this.routineStep = routineStep;
	}

	public int getDisease() {
		return disease;
	}

	public void setDisease(int disease) {
		this.disease = disease;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "RoutineDTO [routineSEQ=" + routineSEQ + ", fitnessObj=" + fitnessObj + ", cardioObj=" + cardioObj
				+ ", name=" + name + ", description=" + description + ", routineStep=" + routineStep + ", disease="
				+ disease + ", period=" + period + "]";
	}
}
