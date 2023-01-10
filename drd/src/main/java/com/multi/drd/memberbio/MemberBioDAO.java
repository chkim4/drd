package com.multi.drd.memberbio;

public interface MemberBioDAO {
	int register(MemberBioDTO registerMemberBio); 
	
	MemberBioDTO findByPK(int memberSEQ);  
}
