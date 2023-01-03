package com.multi.drd.dashboard;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
@Repository
public class DashboarDAOImpl implements DashboardDAO {
	SqlSession sqlSession;
	
	@Autowired
	public DashboarDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}
	


	public DashboarDAOImpl() {
		super();
	}



	@Override
	public MemberDTO read(String id) {
		// TODO Auto-generated method stub
		return  sqlSession.selectOne("com.multi.drd.dashboard.read", id);
	}



	@Override
	public MemberBioDTO readbio(String id) {
		// TODO Auto-generated method stub
		return  sqlSession.selectOne("com.multi.drd.dashboard.readbio", id);
	}

}
