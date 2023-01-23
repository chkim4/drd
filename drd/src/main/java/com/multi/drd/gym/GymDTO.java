package com.multi.drd.gym;

public class GymDTO {
	private int gymSEQ;
	private String name;
	private String address;
	private String telephoneNumber;
	private int memberSEQ;
	
	
	public GymDTO() {
		super();
	}


	public GymDTO(String name, String address, String telephoneNumber, int memberSEQ) {
		super();
		this.name = name;
		this.address = address;
		this.telephoneNumber = telephoneNumber;
		this.memberSEQ = memberSEQ;
	}



	public int getGymSEQ() {
		return gymSEQ;
	}


	public void setGymSEQ(int gymSEQ) {
		this.gymSEQ = gymSEQ;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getTelephoneNumber() {
		return telephoneNumber;
	}


	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}


	public int getMemberSEQ() {
		return memberSEQ;
	}


	public void setMemberSEQ(int memberSEQ) {
		this.memberSEQ = memberSEQ;
	}


	@Override
	public String toString() {
		return "GymDTO [gymSEQ=" + gymSEQ + ", name=" + name + ", address=" + address + ", telephoneNumber="
				+ telephoneNumber + ", memberSEQ=" + memberSEQ + "]";
	}
	
	
	
	

}
