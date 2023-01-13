package com.multi.drd.cardio;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CardioDAOImpl implements CardioDAO {
	SqlSession sqlSession;
	public CardioDAOImpl() {}
	@Autowired
	public CardioDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public CardioDTO findOne(int cardioSEQ) {
		return sqlSession.selectOne("com.multi.drd.cardio.findOne", cardioSEQ);
	}

}
