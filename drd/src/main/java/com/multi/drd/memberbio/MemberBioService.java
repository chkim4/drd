package com.multi.drd.memberbio;


public interface MemberBioService {
	// 회원가입 
	int register(MemberBioDTO registerMemberBio, int gender); 
	
	// 현재 사용자의 bio 정보 구하기
	MemberBioDTO findByPK(int memberSEQ);
}
