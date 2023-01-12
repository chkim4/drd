package com.multi.drd.memberbio;

import java.sql.Date;

public class MemberBioDTO {
	
	private int memberBioSEQ; // memberSEQ와 대응. 이름 중복이 안 되서 따로 씀.
	private double height;
	private double weight;
	private double bfp;
	private double mp;
	private double bmr;
	private double activityLevel;
	private double waistline;
	private Date createdAt;
	
	public MemberBioDTO() {
		super();
	}
	
	// 회원 가입 시 필요한 최소 정보
	public MemberBioDTO(double height, double weight, Date createdAt) {
		super();
		this.height = height;
		this.weight = weight;
		this.createdAt = createdAt;
	}
	
	// getter & setter
	public int getMemberBioSEQ() {
		return memberBioSEQ;
	}

	public void setMemberBioSEQ(int memberBioSEQ) {
		this.memberBioSEQ = memberBioSEQ;
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

	public double getWaistline() {
		return waistline;
	}

	public void setWaistline(double waistline) {
		this.waistline = waistline;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "MemberBioDTO [memberBioSEQ=" + memberBioSEQ + ", height=" + height + ", weight=" + weight + ", bfp="
				+ bfp + ", mp=" + mp + ", bmr=" + bmr + ", activityLevel=" + activityLevel + ", waistline=" + waistline
				+ ", createdAt=" + createdAt + "]";
	}  	
}
