package com.multi.drd.cardio;

import java.util.List;
import java.util.Map;

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
	@Override
	public List<CardioDTO> findByIntensity(int intensity) {
		return sqlSession.selectList("com.multi.drd.cardio.findByIntensity", intensity);
	}
	@Override
	public List<CardioDTO> findByIntensitywithseq(Map<String, Object> map) {
		return sqlSession.selectList("com.multi.drd.cardio.findByIntensitywithSEQ", map);
	}
	@Override
	public List<CardioDTO> findbyname(String name) {
		return sqlSession.selectList("com.multi.drd.cardio.findbyname", name);
	}

}
