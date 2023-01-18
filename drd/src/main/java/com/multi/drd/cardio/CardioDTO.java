package com.multi.drd.cardio;

public class CardioDTO {
	private int cardioSEQ;
	private String name;
	private int intensity;
	private int calory;
	public CardioDTO() {}
	public CardioDTO(int cardioSEQ, String name, int intensity, int calory) {
		super();
		this.cardioSEQ = cardioSEQ;
		this.name = name;
		this.intensity = intensity;
		this.calory = calory;
	}
	@Override
	public String toString() {
		return "CardioDTO [cardioSEQ=" + cardioSEQ + ", name=" + name + ", intensity=" + intensity + ", calory="
				+ calory + "]";
	}
	public int getCardioSEQ() {
		return cardioSEQ;
	}
	public void setCardioSEQ(int cardioSEQ) {
		this.cardioSEQ = cardioSEQ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIntensity() {
		return intensity;
	}
	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}
	public int getCalory() {
		return calory;
	}
	public void setCalory(int calory) {
		this.calory = calory;
	}
	
	

}
