package com.multi.drd.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.drd.memberbio.MemberBioDTO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	SqlSession sqlSession;
	
	public MemberDAOImpl() {
		
	}
		
	@Autowired
	public MemberDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	} 
	
	@Override
	public int register(MemberDTO registerMember) {
		
		return sqlSession.insert("com.multi.drd.member.register",registerMember);
	} 
	
	@Override
	public MemberDTO login(MemberDTO loginMember) {
		
		return sqlSession.selectOne("com.multi.drd.member.login",loginMember);
	}
	
	@Override
	public MemberDTO findById(String id) {
		
		return sqlSession.selectOne("com.multi.drd.member.findById", id);
	}

	@Override
	public MemberDTO findByNickName(String nickName) {
		
		return sqlSession.selectOne("com.multi.drd.member.findByNickName", nickName);
	}

	@Override
	public MemberDTO findByEmail(String email) {
		return sqlSession.selectOne("com.multi.drd.member.findByEmail", email);
	}
}
