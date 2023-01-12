package com.multi.drd.member;

import java.sql.Date;

public class MemberDTO {
	private int memberSEQ; 
	private int personalRoutineSEQ; 
	private int gymSEQ; 
	private String email; 
	private String pass;
	private String nickName;
	private String profile;	
	private String profileComment;
	private int age;
	private Date birth; //date
	private String location;
	private int gender; //0: 여성, 1: 남성 
	private int disease; 
	private int routineStep;
	private String currentBodyShape;
	private double desiredWeight; 	
	private String desiredArea;
	private String desiredBodyShape;
	private Date createdAt; //date  
	
	public MemberDTO() {
		super();
	}
	// 회원 가입 시 필요한 최소 정보
	public MemberDTO(String email, String pass, String nickName, int age, Date birth, int gender, int disease,
			int routineStep, Date createdAt) {
		super();
		this.email = email;
		this.pass = pass;
		this.nickName = nickName;
		this.age = age;
		this.birth = birth;
		this.gender = gender;
		this.disease = disease;
		this.routineStep = routineStep;
		this.createdAt = createdAt;
	} 
	
	//getter & setter 
	public int getMemberSEQ() {
		return memberSEQ;
	}
	public void setMemberSEQ(int memberSEQ) {
		this.memberSEQ = memberSEQ;
	}
	public int getPersonalRoutineSEQ() {
		return personalRoutineSEQ;
	}
	public void setPersonalRoutineSEQ(int personalRoutineSEQ) {
		this.personalRoutineSEQ = personalRoutineSEQ;
	}
	public int getGymSEQ() {
		return gymSEQ;
	}
	public void setGymSEQ(int gymSEQ) {
		this.gymSEQ = gymSEQ;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getProfileComment() {
		return profileComment;
	}
	public void setProfileComment(String profileComment) {
		this.profileComment = profileComment;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getDisease() {
		return disease;
	}
	public void setDisease(int disease) {
		this.disease = disease;
	}
	public int getRoutineStep() {
		return routineStep;
	}
	public void setRoutineStep(int routineStep) {
		this.routineStep = routineStep;
	}
	public String getCurrentBodyShape() {
		return currentBodyShape;
	}
	public void setCurrentBodyShape(String currentBodyShape) {
		this.currentBodyShape = currentBodyShape;
	}
	public double getDesiredWeight() {
		return desiredWeight;
	}
	public void setDesiredWeight(double desiredWeight) {
		this.desiredWeight = desiredWeight;
	}
	public String getDesiredArea() {
		return desiredArea;
	}
	public void setDesiredArea(String desiredArea) {
		this.desiredArea = desiredArea;
	}
	public String getDesiredBodyShape() {
		return desiredBodyShape;
	}
	public void setDesiredBodyShape(String desiredBodyShape) {
		this.desiredBodyShape = desiredBodyShape;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "MemberDTO [memberSEQ=" + memberSEQ + ", personalRoutineSEQ=" + personalRoutineSEQ + ", gymSEQ=" + gymSEQ
				+ ", email=" + email + ", pass=" + pass + ", nickName=" + nickName + ", profile=" + profile
				+ ", profileComment=" + profileComment + ", age=" + age + ", birth=" + birth + ", location=" + location
				+ ", gender=" + gender + ", disease=" + disease + ", routineStep=" + routineStep + ", currentBodyShape="
				+ currentBodyShape + ", desiredWeight=" + desiredWeight + ", desiredArea=" + desiredArea
				+ ", desiredBodyShape=" + desiredBodyShape + ", createdAt=" + createdAt + "]";
	}	
}
