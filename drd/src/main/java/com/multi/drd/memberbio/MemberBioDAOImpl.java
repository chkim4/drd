package com.multi.drd.memberbio;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberBioDAOImpl implements MemberBioDAO {
	
	SqlSession sqlSession; 
	
	public MemberBioDAOImpl() {
		super();
	}
	
	@Autowired
	public MemberBioDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public int register(MemberBioDTO registerMemberBio) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.multi.drd.memberBio.register",registerMemberBio);
	}

}
