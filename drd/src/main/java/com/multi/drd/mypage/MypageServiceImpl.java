package com.multi.drd.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
@Service
public class MypageServiceImpl implements MypageService {
	MypageDAO dao;
	
	@Autowired
	public MypageServiceImpl(MypageDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public MemberBioDTO getMemberBio(int memberseq) {
		return dao.getMemberBio(memberseq);
	}

	@Override
	public int updateInfo(MemberDTO member) {
		return dao.updateInfo(member);
	}

	@Override
	public String pwCheck(String memberSEQ) {
		return dao.pwCheck(memberSEQ);
	}

	@Override
	public int updatePwd(MemberDTO member) {
		return dao.updatePwd(member);
	}

	@Override
	public int deleteUser(MemberDTO member) {
		return dao.deleteUser(member);
	}


	
	
}
