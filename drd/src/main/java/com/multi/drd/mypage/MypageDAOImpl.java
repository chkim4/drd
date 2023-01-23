package com.multi.drd.mypage;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
@Repository
public class MypageDAOImpl implements MypageDAO{
	SqlSession sqlSession;
	
	@Autowired
	public MypageDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public MemberBioDTO getMemberBio(int memberseq) {
		return sqlSession.selectOne("com.multi.drd.memberBio.findByPK", memberseq);
	}

	@Override
	public int updateInfo(MemberDTO member) {
		return sqlSession.update("com.multi.drd.mypage.updateInfo", member);
	}

	@Override
	public String pwCheck(String memberSEQ) {
		return sqlSession.selectOne("com.multi.drd.mypage.pwCheck", memberSEQ);
	}
	
	@Override
	public int updatePwd(MemberDTO member) {
		return sqlSession.update("com.multi.drd.mypage.updatePwd", member);
	}

	@Override
	public int deleteUser(MemberDTO member) {
		return sqlSession.delete("com.multi.drd.mypage.delete", member);
	}






}
