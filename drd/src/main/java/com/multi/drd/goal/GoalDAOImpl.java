package com.multi.drd.goal;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GoalDAOImpl implements GoalDAO {
	SqlSession sqlSession;
	MongoTemplate mongoTemplate;
	
	public GoalDAOImpl() {}
	
	@Autowired
	public GoalDAOImpl(SqlSession sqlSession, MongoTemplate mongoTemplate) {
		super();
		this.sqlSession = sqlSession;
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public GoalDTO readGoal(int memberSEQ) {
		return sqlSession.selectOne("com.multi.drd.goal.readGoal", memberSEQ);
	}

	@Override
	public int updateTime(GoalDTO goal) {
		return sqlSession.update("com.multi.drd.goal.updateTime", goal);
	}

	@Override
	public int updateCalory(GoalDTO goal) {
		return sqlSession.update("com.multi.drd.goal.updateCalory", goal);
	}


	
	
}
