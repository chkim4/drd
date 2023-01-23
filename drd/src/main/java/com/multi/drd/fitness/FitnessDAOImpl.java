package com.multi.drd.fitness;

import java.util.List;
import java.util.Map;

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
	@Override
	public List<FitnessDTO> findbymusclegroup(String musclegroup) {
		return sqlSession.selectList("com.multi.drd.fitness.findbymusclegroup", musclegroup);
	}
	@Override
	public List<FitnessDTO> findbymusclegroupwithSEQ(Map<String, Object> map) {
		return sqlSession.selectList("com.multi.drd.fitness.findbymusclegroupwithSEQ", map);
	}
	@Override
	public List<FitnessDTO> findbyname(String name) {
		return sqlSession.selectList("com.multi.drd.fitness.findbyname", name);
	}

}
