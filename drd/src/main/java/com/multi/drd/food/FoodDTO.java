package com.multi.drd.food;

public class FoodDTO {
	
	private int foodSEQ; 
	private String name;
	private int quanity; // 1회 섭취량 (g)
	private double calory; // 1회 섭취량 기준 칼로리 
	private double carb; // 1회 섭취량 기준 탄수화물
	private double protein; // 1회 섭취량 기준 단백질 
	private double fat; // 1회 섭취량 기준 지방량
	private double cholesterol;// 1회 섭취량 기준 콜레스트롤 
	
	public FoodDTO() {
		super();
	} 
	
	public FoodDTO(int foodSEQ, String name, int quanity, double calory, double carb, double protein, double fat,
			double cholesterol) {
		super();
		this.foodSEQ = foodSEQ;
		this.name = name;
		this.quanity = quanity;
		this.calory = calory;
		this.carb = carb;
		this.protein = protein;
		this.fat = fat;
		this.cholesterol = cholesterol;
	}

	public int getFoodSEQ() {
		return foodSEQ;
	}
	public void setFoodSEQ(int foodSEQ) {
		this.foodSEQ = foodSEQ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	public double getCalory() {
		return calory;
	}
	public void setCalory(double calory) {
		this.calory = calory;
	}
	public double getCarb() {
		return carb;
	}
	public void setCarb(double carb) {
		this.carb = carb;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getCholesterol() {
		return cholesterol;
	}
	public void setCholesterol(double cholesterol) {
		this.cholesterol = cholesterol;
	}
	
	@Override
	public String toString() {
		return "FoodDTO [foodSEQ=" + foodSEQ + ", name=" + name + ", quanity=" + quanity + ", calory=" + calory
				+ ", carb=" + carb + ", protein=" + protein + ", fat=" + fat + ", cholesterol=" + cholesterol + "]";
	}
}
