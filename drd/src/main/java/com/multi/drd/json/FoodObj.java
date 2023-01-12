package com.multi.drd.json;

public class FoodObj {
	private int foodSEQ; 
	private String name;
	private int amount;
	private int calory;
	private int protein;
	private int fat;
	private int cholesterol;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(int cholesterol) {
		this.cholesterol = cholesterol;
	}

	@Override
	public String toString() {
		return "FoodObj [foodSEQ=" + foodSEQ + ", name=" + name + ", amount=" + amount + ", calory=" + calory
				+ ", protein=" + protein + ", fat=" + fat + ", cholesterol=" + cholesterol + "]";
	}
}
