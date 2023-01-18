package com.multi.drd.personalroutine;

import java.sql.Date;

public class PersonalRoutineDTO {
	private int personalRoutineSEQ; 
	private int routineSEQ;
	private String cardioObj;
	private String fitnessObj;
	private Date createdAt;
	
	public PersonalRoutineDTO() {
		super();
	}

	public PersonalRoutineDTO(int personalRoutineSEQ, int routineSEQ, String cardioObj, String fitnessObj,
			Date createdAt) {
		super();
		this.personalRoutineSEQ = personalRoutineSEQ;
		this.routineSEQ = routineSEQ;
		this.cardioObj = cardioObj;
		this.fitnessObj = fitnessObj;
		this.createdAt = createdAt;
	}

	public int getPersonalRoutineSEQ() {
		return personalRoutineSEQ;
	}

	public void setPersonalRoutineSEQ(int personalRoutineSEQ) {
		this.personalRoutineSEQ = personalRoutineSEQ;
	}

	public int getRoutineSEQ() {
		return routineSEQ;
	}

	public void setRoutineSEQ(int routineSEQ) {
		this.routineSEQ = routineSEQ;
	}

	public String getCardioObj() {
		return cardioObj;
	}

	public void setCardioObj(String cardioObj) {
		this.cardioObj = cardioObj;
	}

	public String getFitnessObj() {
		return fitnessObj;
	}

	public void setFitnessObj(String fitnessObj) {
		this.fitnessObj = fitnessObj;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "PersonalRoutineDTO [personalRoutineSEQ=" + personalRoutineSEQ + ", routineSEQ=" + routineSEQ
				+ ", cardioObj=" + cardioObj + ", fitnessObj=" + fitnessObj + ", createdAt=" + createdAt + "]";
	}
}

