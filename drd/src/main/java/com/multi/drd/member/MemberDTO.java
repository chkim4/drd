package com.multi.drd.member;

import java.sql.Date;

public class MemberDTO {
	private int memberSEQ; 
	private int gymSEQ;
	private String nickName;
	private String profile;
	private String id;
	private String pass;
	private String profileComment;
	private Date birth; //date
	private String location;
	private String gender; //0: 여성, 1: 남성 
	private String currentBodyShape; 
	private int desiredWeight; 	
	private String desiredArea;
	private String desiredBodyShape;
	private Date createdAt; //date  
	
	private String email;
	
	
	public MemberDTO() {
		super();
	}

	// 회원 가입 시 필요한 최소 정보
	public MemberDTO(int memberSEQ, int gymSEQ, String nickName, String id, String pass, Date birth, String gender,
			Date createdAt) {
		super();
		this.memberSEQ = memberSEQ;
		this.gymSEQ = gymSEQ;
		this.nickName = nickName;
		this.id = id;
		this.pass = pass;
		this.birth = birth;
		this.gender = gender;
		this.createdAt = createdAt;
	}
	
	//setter & getter
	public int getMemberSEQ() {
		return memberSEQ;
	}

	public void setMemberSEQ(int memberSEQ) {
		this.memberSEQ = memberSEQ;
	}

	public int getGymSEQ() {
		return gymSEQ;
	}

	public void setGymSEQ(int gymSEQ) {
		this.gymSEQ = gymSEQ;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getProfileComment() {
		return profileComment;
	}

	public void setProfileComment(String profileComment) {
		this.profileComment = profileComment;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCurrentBodyShape() {
		return currentBodyShape;
	}

	public void setCurrentBodyShape(String currentBodyShape) {
		this.currentBodyShape = currentBodyShape;
	}

	public int getDesiredWeight() {
		return desiredWeight;
	}

	public void setDesiredWeight(int desiredWeight) {
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "MemberDTO [memberSEQ=" + memberSEQ + ", gymSEQ=" + gymSEQ + ", nickName=" + nickName + ", profile="
				+ profile + ", id=" + id + ", pass=" + pass + ", profileComment=" + profileComment + ", birth=" + birth
				+ ", location=" + location + ", gender=" + gender + ", currentBodyShape=" + currentBodyShape
				+ ", desiredWeight=" + desiredWeight + ", desiredArea=" + desiredArea + ", desiredBodyShape="
				+ desiredBodyShape + ", createdAt=" + createdAt + ", email=" + email + "]";
	}
	
	
}
