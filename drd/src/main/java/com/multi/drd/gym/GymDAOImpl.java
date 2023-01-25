package com.multi.drd.gym;

import java.util.HashMap;

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
	public int insertGym(GymDTO gym) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.multi.drd.gym.insertGym", gym);
	}

	@Override
	public int updateGym(HashMap<String, Integer> param) {
		// TODO Auto-generated method stub
		return sqlSession.update("com.multi.drd.gym.updateGymSEQ", param);
	}

	
	@Override
	public GymDTO findByGymName(String name) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.multi.drd.gym.findByGymName", name);
	}
	@Override
	public int deleteGym(int memberSEQ) {
		// TODO Auto-generated method stub
		return sqlSession.delete("com.multi.drd.gym.deleteGymSEQ", memberSEQ);
	}

}
