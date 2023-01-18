package com.multi.drd.goal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.record.RecordDTO;
import com.multi.drd.utils.DateUtils;

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

	@Override
	public List<MemberDTO> getBodyShapeList() {
		return sqlSession.selectList("com.multi.drd.goal.getBodyShapeList");
	}

	@Override
	public int updateBodyShape(MemberDTO member) {
		return sqlSession.update("com.multi.drd.goal.updateBodyShape", member);
	}

	public List<RecordDTO> findByWeek(int memberSEQ) {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date today = c.getTime();
        c.add(c.DATE, -7);
        Date stDate = c.getTime();
        Query query = new Query(
                  Criteria.where("memberSEQ").is(memberSEQ)
                  .andOperator(
                		  	Criteria.where("date").gte(DateUtils.getDailyISODate(stDate)[0]),
			                Criteria.where("date").lte(DateUtils.getDailyISODate(today)[1])
//                            Criteria.where("date").gte(DateUtils.getISODate("2023-01-09","start")),
//                            Criteria.where("date").lte(DateUtils.getISODate("2023-01-15","end"))
                            
                        )
                );
        List<RecordDTO> docs =  mongoTemplate.find(query, RecordDTO.class, "record");
        System.out.println("in dao findByWeek" + docs);
        return docs;
    }
	
	
}
