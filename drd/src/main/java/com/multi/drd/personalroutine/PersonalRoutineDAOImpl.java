package com.multi.drd.personalroutine;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonalRoutineDAOImpl implements PersonalRoutineDAO{
	SqlSession sqlSession;
	
	public PersonalRoutineDAOImpl() {}
		
	@Autowired
	public PersonalRoutineDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	} 
	
	public PersonalRoutineDTO findOne() {
		return sqlSession.selectOne("com.multi.drd.personalroutine.findOne");
	}

	public int updateOne(PersonalRoutineDTO pr) {
		return sqlSession.update("com.multi.drd.personalroutine.updateOne", pr);
	}
	
}
