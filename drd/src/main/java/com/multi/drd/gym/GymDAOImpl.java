package com.multi.drd.gym;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GymDAOImpl implements GymDAO {
	SqlSession sqlSession;
	
	
	public GymDAOImpl() {
		super();
	}
	@Autowired
	public GymDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public GymDTO readGym(int memberSEQ) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.multi.drd.gym.readGym", memberSEQ);
	}

	@Override
	public int registerGym(GymDTO gym) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.multi.drd.gym.registerGym", gym);
	}

	@Override
	public int updateGym(GymDTO gym) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.multi.drd.gym.updateGym", gym);
	}

	@Override
	public int deleteGym(int memberSEQ) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.multi.drd.gym.deleteGym", memberSEQ);
	}

}
