package com.multi.drd.json;

import java.util.List;

public class FitnessObj{
	
	private int totalTime; 
	private List<FitnessList> fitnessList;  
	
	public FitnessObj() {} 
		
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	} 
	
	public List<FitnessList> getFitnessList() {
		return fitnessList;
	}

	public void setFitnessList(List<FitnessList> fitnessList) {
		this.fitnessList = fitnessList;
	} 
	
	// inner class
	public static class FitnessList{
		private int fitnessSEQ;
		private int set;
		private int count;
		private int weight; 
		private String name; 
		private String muscleGroup; 
		private String equipment; 
		
		
		public FitnessList() {} 
		
		public FitnessList(int fitnessSEQ, int set, int count, int weight) {
			super();
			this.fitnessSEQ = fitnessSEQ;
			this.set = set;
			this.count = count;
			this.weight = weight;
		}

		public int getFitnessSEQ() {
			return fitnessSEQ;
		}
		public void setFitnessSEQ(int fitnessSEQ) {
			this.fitnessSEQ = fitnessSEQ;
		}
		
		public int getSet() {
			return set;
		}
		public void setSet(int set) {
			this.set = set;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
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

		@Override
		public String toString() {
			return "FitnessList [fitnessSEQ=" + fitnessSEQ + ", set=" + set + ", count=" + count + ", weight=" + weight
					+ "]";
		} 	 
	}



	@Override
	public String toString() {
		return "FitnessObj [totalTime=" + totalTime + ", fitnessList=" + fitnessList + "]";
	}
}


