package com.multi.drd.record;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.multi.drd.json.CardioObj;
import com.multi.drd.json.FitnessObj;
import com.multi.drd.json.FoodObj;

@Document(collection = "record")
public class RecordDTO {
	@Id
	private String _id;
	private int memberSEQ;
	private Date date;  
	private int totalExerciseTime;
	private int totalCalory;  
	private int status; 
	private double weight; 
	private double bfp; 
	private double mp; 
	private CardioObj cardioObj;
	private FitnessObj fitnessObj;
	private List<FoodObj> foodObj; 
	
	public RecordDTO() {
		super();
	}

	public RecordDTO(String _id, int memberSEQ, Date date, int totalExerciseTime, int totalCalory, int status,
			double weight, double bfp, double mp, CardioObj cardioObj, FitnessObj fitnessObj, List<FoodObj> foodObj) {
		super();
		this._id = _id;
		this.memberSEQ = memberSEQ;
		this.date = date;
		this.totalExerciseTime = totalExerciseTime;
		this.totalCalory = totalCalory;
		this.status = status;
		this.weight = weight;
		this.bfp = bfp;
		this.mp = mp;
		this.cardioObj = cardioObj;
		this.fitnessObj = fitnessObj;
		this.foodObj = foodObj;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTotalExerciseTime() {
		return totalExerciseTime;
	}
	public void setTotalExerciseTime(int totalExerciseTime) {
		this.totalExerciseTime = totalExerciseTime;
	}
	public int getTotalCalory() {
		return totalCalory;
	}
	public void setTotalCalory(int totalCalory) {
		this.totalCalory = totalCalory;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getBfp() {
		return bfp;
	}
	public void setBfp(double bfp) {
		this.bfp = bfp;
	}
	public double getMp() {
		return mp;
	}
	public void setMp(double mp) {
		this.mp = mp;
	}
	public CardioObj getCardioObj() {
		return cardioObj;
	}
	public void setCardioObj(CardioObj cardioObj) {
		this.cardioObj = cardioObj;
	}
	public FitnessObj getFitnessObj() {
		return fitnessObj;
	}
	public void setFitnessObj(FitnessObj fitnessObj) {
		this.fitnessObj = fitnessObj;
	}
	public List<FoodObj> getFoodObj() {
		return foodObj;
	}
	public void setFoodObj(List<FoodObj> foodObj) {
		this.foodObj = foodObj;
	}

	@Override
	public String toString() {
		return "RecordDTO [_id=" + _id + ", memberSEQ=" + memberSEQ + ", date=" + date + ", totalExerciseTime="
				+ totalExerciseTime + ", totalCalory=" + totalCalory + ", status=" + status + ", weight=" + weight
				+ ", bfp=" + bfp + ", mp=" + mp + ", cardioObj=" + cardioObj + ", fitnessObj=" + fitnessObj
				+ ", foodObj=" + foodObj + "]";
	} 
	
	
	
}
