package com.multi.drd.mypage;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;

public interface MypageService {
	MemberBioDTO getMemberBio(int memberseq);

	int updateInfo(MemberDTO member);

	String pwCheck(String memberSEQ);
	
	int updatePwd(MemberDTO member);
	
	int deleteUser(MemberDTO member);

}
