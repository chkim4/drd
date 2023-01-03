package com.multi.drd.member;

public interface MemberService {
	
	// 회원가입 
	int register(MemberDTO registerMember);
	
	// 로그인 
	MemberDTO login(MemberDTO loginMember);
	
	//아이디로 검색
	MemberDTO findById(String id);
	
	//닉네임으로 검색 
	MemberDTO findByNickName(String nickName);
	
	//이메일로 검색 
	MemberDTO findByEmail(String email);
	
}
