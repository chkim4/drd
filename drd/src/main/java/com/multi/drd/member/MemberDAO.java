package com.multi.drd.member;

public interface MemberDAO {
	
	int register(MemberDTO registerMember);
	
	MemberDTO login(MemberDTO loginMember);  
	
	MemberDTO findById(String id); 

	MemberDTO findByNickName(String nickName); 
	
	MemberDTO findByEmail(String email);  	
}
