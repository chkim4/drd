package com.multi.drd.json;

public class FoodObj {
	private int foodSEQ;
	private int amount;
	private int calory;
	private int protein;
	
	public FoodObj() {
		super();
	}

	public FoodObj(int foodSEQ, int amount, int calory, int protein) {
		super();
		this.foodSEQ = foodSEQ;
		this.amount = amount;
		this.calory = calory;
		this.protein = protein;
	}

	public int getFoodSEQ() {
		return foodSEQ;
	}

	public void setFoodSEQ(int foodSEQ) {
		this.foodSEQ = foodSEQ;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCalory() {
		return calory;
	}

	public void setCalory(int calory) {
		this.calory = calory;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	@Override
	public String toString() {
		return "FoodObj [foodSEQ=" + foodSEQ + ", amount=" + amount + ", calory=" + calory + ", protein=" + protein
				+ "]";
	}	
}
