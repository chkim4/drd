package com.multi.drd.dashboard;

public class AggregationResultDTO {
	private String _id;
	private String totalExerciseTimeofWeek;
	
	public AggregationResultDTO() {
		
	}
	public AggregationResultDTO(String _id, String totalExerciseTimeofWeek) {
		// TODO Auto-generated constructor stub
		this._id = _id;
		this.totalExerciseTimeofWeek = totalExerciseTimeofWeek;
	}


	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}


	public String getTotalExerciseTimeofWeek() {
		return totalExerciseTimeofWeek;
	}


	public void setTotalExerciseTimeofWeek(String totalExerciseTimeofWeek) {
		this.totalExerciseTimeofWeek = totalExerciseTimeofWeek;
	}


	@Override
	public String toString() {
		return "AggregationResultDTO [_id=" + _id + ", totalExerciseTimeofWeek=" + totalExerciseTimeofWeek + "]";
	}
	


	

}
