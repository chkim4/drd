package com.multi.drd.record;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "record")
public class RecordDTO {
	@Id
	String _id;
	String memberSEQ;
	List<Integer> foodList;
	List<Integer> exerciseList;
	Date date;
	int totalExerciseHour;
	int weight;
	int bfp;
	int mp;
 	
	public RecordDTO() {}
	
	public RecordDTO(String _id, String memberSEQ, List<Integer> foodList, List<Integer> exerciseList, Date date,
			int totalExerciseHour, int weight, int bfp, int mp) {
		super();
		
		this._id = _id;
		this.memberSEQ = memberSEQ;
		this.foodList = foodList;
		this.exerciseList = exerciseList;
		this.date = date;
		this.totalExerciseHour = totalExerciseHour;
		this.weight = weight;
		this.bfp = bfp;
		this.mp = mp;
	} 
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	} 
	
	public String getMemberSEQ() {
		return memberSEQ;
	}
	public void setMemberSEQ(String memberSEQ) {
		this.memberSEQ = memberSEQ;
	}
	public List<Integer> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<Integer> foodList) {
		this.foodList = foodList;
	}
	public List<Integer> getExerciseList() {
		return exerciseList;
	}
	public void setExerciseList(List<Integer> exerciseList) {
		this.exerciseList = exerciseList;
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
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getBfp() {
		return bfp;
	}
	public void setBfp(int bfp) {
		this.bfp = bfp;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}

	@Override
	public String toString() {
		return "RecordDTO [_id=" + _id + ", memberSEQ=" + memberSEQ + ", foodList=" + foodList + ", exerciseList="
				+ exerciseList + ", date=" + date + ", totalExerciseHour=" + totalExerciseHour + ", weight=" + weight
				+ ", bfp=" + bfp + ", mp=" + mp + "]";
	}  	
}
