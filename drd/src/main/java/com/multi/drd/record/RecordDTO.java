package com.multi.drd.record;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import com.multi.drd.json.CardioObj;
import com.multi.drd.json.FitnessObj;

@Document(collection = "record")
public class RecordDTO {
	@Id
	private String _id;
	private int memberSEQ;
	private CardioObj cardioList;
	private FitnessObj fitnessList;
	private Date date;  
	private int totalExerciseHour;
	private int totalCalory; 
	
	public RecordDTO() {
		super();
	}
	
	public RecordDTO(String _id, int memberSEQ, CardioObj cardioList, FitnessObj fitnessList, Date date,
			int totalExerciseHour, int totalCalory) {
		super();
		this._id = _id;
		this.memberSEQ = memberSEQ;
		this.cardioList = cardioList;
		this.fitnessList = fitnessList;
		this.date = date;
		this.totalExerciseHour = totalExerciseHour;
		this.totalCalory = totalCalory;
	}

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public int getMemberSEQ() {
		return memberSEQ;
	}
	public void setMemberSEQ(int memberSEQ) {
		this.memberSEQ = memberSEQ;
	}
	public CardioObj getCardioList() {
		return cardioList;
	}
	public void setCardioList(CardioObj cardioList) {
		this.cardioList = cardioList;
	}
	public FitnessObj getFitnessList() {
		return fitnessList;
	}
	public void setFitnessList(FitnessObj fitnessList) {
		this.fitnessList = fitnessList;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTotalExerciseHour() {
		return totalExerciseHour;
	}
	public void setTotalExerciseHour(int totalExerciseHour) {
		this.totalExerciseHour = totalExerciseHour;
	}
	public int getTotalCalory() {
		return totalCalory;
	}
	public void setTotalCalory(int totalCalory) {
		this.totalCalory = totalCalory;
	}

	@Override
	public String toString() {
		return "RecordDTO [_id=" + _id + ", memberSEQ=" + memberSEQ + ", cardioList=" + cardioList + ", fitnessList="
				+ fitnessList + ", date=" + date + ", totalExerciseHour=" + totalExerciseHour + ", totalCalory="
				+ totalCalory + "]";
	} 
}
