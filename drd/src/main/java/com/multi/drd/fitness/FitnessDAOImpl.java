package com.multi.drd.fitness;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FitnessDAOImpl implements FitnessDAO {
	SqlSession sqlSession;
	public FitnessDAOImpl() {}
	@Autowired
	public FitnessDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public FitnessDTO findOne(int fitnessSEQ) {
		return sqlSession.selectOne("com.multi.drd.fitness.findOne", fitnessSEQ);
	}

}
