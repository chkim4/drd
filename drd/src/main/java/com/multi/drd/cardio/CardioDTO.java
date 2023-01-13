package com.multi.drd.cardio;

public class CardioDTO {
String name;
int intensity;
public CardioDTO() {}
public CardioDTO(String name, int intensity) {
	super();
	this.name = name;
	this.intensity = intensity;
}
@Override
public String toString() {
	return "CardioDTO [name=" + name + ", intensity=" + intensity + "]";
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

}
