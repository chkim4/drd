package com.multi.drd.memberbio;

import java.sql.Date;

public class MemberBioDTO {
	
	private int memberBioSEQ; // memberSEQ와 대응. 이름 중복이 안 되서 따로 씀.
	private int age;
	private int gender; // 1: 남성, 0: 여성
	private double height;
	private double weight;
	private double waistline;
	private double bfp;
	private double mp;
	private double bmr;
	private double activityLevel;
	private Date createdAt;
	
	public MemberBioDTO() {
		super();
	} 
	
	// 회원 가입 시 필요한 최소 정보 
	public MemberBioDTO(int age, int gender, double height, double weight, Date createdAt) {
		super();
		this.age = age;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.createdAt = createdAt;
	}
	
	public MemberBioDTO(int memberBioSEQ, int gender, int age, double height, double weight, double waistline, double bfp,
			double mp, double bmr, double activityLevel, Date createdAt) {
		super();
		this.memberBioSEQ = memberBioSEQ;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.waistline = waistline;
		this.bfp = bfp;
		this.mp = mp;
		this.bmr = bmr;
		this.activityLevel = activityLevel;
		this.createdAt = createdAt;
	} 
	
	public int getMemberBioSEQ() {
		return memberBioSEQ;
	}

	public void setMemberBioSEQ(int memberBioSEQ) {
		this.memberBioSEQ = memberBioSEQ;
	}

	public int getGender() {
		return gender;
	}
	
	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWaistline() {
		return waistline;
	}

	public void setWaistline(double waistline) {
		this.waistline = waistline;
	}

	public double getBfp() {
		return bfp;
	}

	public void setBfp(double bfp) {
		this.bfp = bfp;
	}

	public double getMp() {
		return mp;
	}

	public void setMp(double mp) {
		this.mp = mp;
	}

	public double getBmr() {
		return bmr;
	}

	public void setBmr(double bmr) {
		this.bmr = bmr;
	}

	public double getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(double activityLevel) {
		this.activityLevel = activityLevel;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "MemberBioDTO [memberBioSEQ=" + memberBioSEQ + ", age=" + age + ", gender=" + gender + ", height="
				+ height + ", weight=" + weight + ", waistline=" + waistline + ", bfp=" + bfp + ", mp=" + mp + ", bmr="
				+ bmr + ", activityLevel=" + activityLevel + ", createdAt=" + createdAt + "]";
	}  	 
	
}
