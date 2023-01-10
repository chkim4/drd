package com.multi.drd.json;

import java.util.List;

public class CardioObj{ 
	
	private int totalTime; 
	private List<CardioList> cardioList;  
	
	public CardioObj() {} 
		
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	
	public List<CardioList> getCardioList() {
		return cardioList;
	}

	public void setCardioList(List<CardioList> cardioList) {
		this.cardioList = cardioList;
	}

	// inner class
	public static class CardioList{
		private int cardioSEQ;
		private int time;
		private int calory; 
		
		public CardioList() {}

		public int getCardioSEQ() {
			return cardioSEQ;
		} 
		
		public CardioList(int cardioSEQ, int time, int calory) {
			super();
			this.cardioSEQ = cardioSEQ;
			this.time = time;
			this.calory = calory;
		}

		public void setCardioSEQ(int cardioSEQ) {
			this.cardioSEQ = cardioSEQ;
		}
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
		public int getCalory() {
			return calory;
		}
		public void setCalory(int calory) {
			this.calory = calory;
		}

		@Override
		public String toString() {
			return "CardioList [cardioSEQ=" + cardioSEQ + ", time=" + time + ", calory=" + calory + "]";
		}  
	}

	@Override
	public String toString() {
		return "CardioObj [totalTime=" + totalTime + ", cardioList=" + cardioList + "]";
	}
}


