package com.multi.drd.json;

public class FoodObj {
	private int foodSEQ; 
	private String name;
	private int quantity;
	private int calory;
	private int carb;
	private int protein;
	private int fat;
	private int cholesterol;
	
	public FoodObj() {
		super();
	}

	public int getFoodSEQ() {
		return foodSEQ;
	}

	public void setFoodSEQ(int foodSEQ) {
		this.foodSEQ = foodSEQ;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	} 

	public int getCalory() {
		return calory;
	}

	public void setCalory(int calory) {
		this.calory = calory;
	} 
	
	public int getCarb() {
		return carb;
	}
	
	public void setCarb(int carb) {
		this.carb = carb;
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
		return "FoodObj [foodSEQ=" + foodSEQ + ", name=" + name + ", quantity=" + quantity + ", calory=" + calory
				+ ", carb=" + carb + ", protein=" + protein + ", fat=" + fat + ", cholesterol=" + cholesterol + "]";
	}
}
