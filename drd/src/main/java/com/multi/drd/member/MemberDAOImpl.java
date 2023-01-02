package com.multi.drd.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public MemberDTO findByNickName(String nickName) {
		
		return sqlSession.selectOne("com.multi.drd.member.findByNickName", nickName);
	}

	@Override
	public MemberDTO findByEmail(String email) {
		return sqlSession.selectOne("com.multi.drd.member.findByEmail", email);
	}

}
