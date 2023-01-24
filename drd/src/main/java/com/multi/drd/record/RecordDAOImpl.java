package com.multi.drd.record;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.multi.drd.cardio.CardioDTO;
import com.multi.drd.fitness.FitnessDTO;
import com.multi.drd.goal.GoalDTO;
import com.multi.drd.member.MemberDAO;
import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDAO;
import com.multi.drd.memberbio.MemberBioDTO;
import com.multi.drd.utils.DateUtils;

@Repository
public class RecordDAOImpl implements RecordDAO {
	MongoTemplate mongoTemplate;
	SqlSession sqlSession; 
	MemberDAO memberDAO;
	MemberBioDAO memberBioDAO;
	
	@Autowired
	public RecordDAOImpl(MongoTemplate mongoTemplate, SqlSession sqlSession) {
		super();
		this.mongoTemplate = mongoTemplate;
		this.sqlSession = sqlSession;
	}
	
	public RecordDTO findDailyRecord(int memberSEQ, Date date) {
		
		Date[] dates = DateUtils.getDailyISODate(date);
		
		Query query = new Query(); 
		query.addCriteria(Criteria.where("memberSEQ").is(memberSEQ));
	    query.addCriteria(Criteria.where("date").gte(dates[0]).lte(dates[1]));
		
	    return mongoTemplate.findOne(query, RecordDTO.class, "record");
	}
	

	@Override
	public RecordDTO findLatestRecord(int memberSEQ) {
		
		Criteria criteria = new Criteria("memberSEQ");
		criteria.is(memberSEQ);
		Query query = new Query(criteria);
		query.with(new Sort(new Order(Direction.DESC, "date")));
		
		return mongoTemplate.findOne(query, RecordDTO.class, "record");
	}	 
	
	@Override
	public RecordDTO findTodayRecord(int memberSEQ){
		Date today = new Date(); 
		Date[] dates = DateUtils.getDailyISODate(today);
		
		Criteria criteria = new Criteria().andOperator(
				Criteria.where("memberSEQ").is(memberSEQ), 
				Criteria.where("date").gte(dates[0]), 
				Criteria.where("date").lte(dates[1]) 
		);
				
		Query query = new Query(criteria); 
				
		return mongoTemplate.findOne(query, RecordDTO.class, "record");
	}  
	
	@Override
	public List<RecordDTO> findWeeklyRecord(int memberSEQ) {
		Date today = new Date(); 
		Date[] dates = DateUtils.getWeeklyISODate(today); 
		
		Criteria criteria = new Criteria().andOperator(
				Criteria.where("memberSEQ").is(memberSEQ), 
				Criteria.where("date").gte(dates[0]), 
				Criteria.where("date").lte(dates[1]) 
		); 
		
		Query query = new Query(criteria);   
		
		return mongoTemplate.find(query, RecordDTO.class, "record");
	}
	
	@Override
	public List<RecordDTO> findMonthlyRecord(int memberSEQ, int year, int month) {
		
		Date[] dates = DateUtils.getMonthlyISODate(year, month);
		
		Criteria criteria = new Criteria().andOperator(
				Criteria.where("memberSEQ").is(memberSEQ), 
				Criteria.where("date").gte(dates[0]), 
				Criteria.where("date").lte(dates[1]) 
		); 
		
		Query query = new Query(criteria);   
		return mongoTemplate.find(query, RecordDTO.class, "record");
	} 
	
	@Override
	public int createOne(RecordDTO record) {
		mongoTemplate.insert(record, "record"); 
		
		// 성공 여부 확인 위함.
		int result = findDailyRecord(record.getMemberSEQ(), record.getDate()) != null ? 1:0;
		
		return result;
	} 

	@Override
	public int updateCardio(RecordDTO record) {
		
		Query query = new Query();
		Update update = new Update();
		 
	    // where절 조건 
		Date[] dates = DateUtils.getDailyISODate(record.getDate()); 
		
	    query.addCriteria(Criteria.where("memberSEQ").is(record.getMemberSEQ()));
	    query.addCriteria(Criteria.where("date").gte(dates[0]).lte(dates[1]));
	    
	    update.set("cardioObj", record.getCardioObj()); 
	 	update.set("totalExerciseTime", record.getTotalExerciseTime()); 
	    
	   return mongoTemplate.updateFirst(query, update, "record").getN(); // 업데이트 수행에 영향을 받는 칼럼의 갯수 반환
	}

	@Override
	public int updateFitness(RecordDTO record) {
		Query query = new Query();
		Update update = new Update();
		 
	    // where절 조건 
		Date[] dates = DateUtils.getDailyISODate(record.getDate()); 
		
	    query.addCriteria(Criteria.where("memberSEQ").is(record.getMemberSEQ()));
	    query.addCriteria(Criteria.where("date").gte(dates[0]).lte(dates[1]));

	    update.set("fitnessObj", record.getFitnessObj()); 
	    update.set("totalExerciseTime", record.getTotalExerciseTime()); 
	   
	    return mongoTemplate.updateFirst(query, update, "record").getN(); // 업데이트 수행에 영향을 받는 칼럼의 갯수 반환
	}

	@Override
	public int updateFood(RecordDTO record) {
		Query query = new Query();
		Update update = new Update();
		
		// where절 조건 
		Date[] dates = DateUtils.getDailyISODate(record.getDate()); 
		
		query.addCriteria(Criteria.where("memberSEQ").is(record.getMemberSEQ()));
		query.addCriteria(Criteria.where("date").gte(dates[0]).lte(dates[1]));
		
		update.set("foodObj", record.getFoodObj()); 
		update.set("totalCalory", record.getTotalCalory()); 
		
		return mongoTemplate.updateFirst(query, update, "record").getN(); // 업데이트 수행에 영향을 받는 칼럼의 갯수 반환
	}

	@Override
	public int updateStatus(RecordDTO record) {
		Query query = new Query();
		Update update = new Update();
		
		// where절 조건 
		Date[] dates = DateUtils.getDailyISODate(record.getDate()); 
		
		query.addCriteria(Criteria.where("memberSEQ").is(record.getMemberSEQ()));
		query.addCriteria(Criteria.where("date").gte(dates[0]).lte(dates[1]));
		
		update.set("status", record.getStatus()); 
			
		return mongoTemplate.updateFirst(query, update, "record").getN();
	} 
	
	@Override
	public int deleteDailyRecord(RecordDTO record) {
		Query query = new Query();
		 
	    // where절 조건 
		Date[] dates = DateUtils.getDailyISODate(record.getDate()); 
		
	    query.addCriteria(Criteria.where("memberSEQ").is(record.getMemberSEQ()));
	    query.addCriteria(Criteria.where("date").gte(dates[0]).lte(dates[1]));  
	    
		return mongoTemplate.remove(query, "record").getN();
	}

	@Override
	public int deleteField(RecordDTO record, String field) {
		Query query = new Query();
		Update update = new Update();
		 
	    // where절 조건 
		Date[] dates = DateUtils.getDailyISODate(record.getDate()); 
		
	    query.addCriteria(Criteria.where("memberSEQ").is(record.getMemberSEQ()));
	    query.addCriteria(Criteria.where("date").gte(dates[0]).lte(dates[1]));  
	    
	    update.unset(field);
	    
	    // 유산소 혹은 무산소 운동 삭제 시에는 totalExerciseTime에도 반영. record의 totalExerciseTime은 service에서 계산함.
	    if(field.equals("cardioObj")  || field.equals("fitnessObj")) {
	    	update.set("totalExerciseTime", record.getTotalExerciseTime());
	    } 
	    // 식단 삭제 시에는 totalCalory를 0으로 함.
	    else if(field.equals("foodObj")) {
	    	update.set("totalCalory",0);
	    }
	    
	    return mongoTemplate.updateFirst(query, update, "record").getN();
	}

	@Override
	public List<CardioDTO> findAllCardio() {
		return sqlSession.selectList("com.multi.drd.record.findAllCardio");
	}

	@Override
	public List<FitnessDTO> findAllFitness() {
		return sqlSession.selectList("com.multi.drd.record.findAllFitness");
	}

	@Override
	public List<RecordDTO> findWeeklyExerciseRecord(int memberSEQ) {
		
		Date today = new Date(); 
		Date[] dates = DateUtils.getWeeklyISODate(today); 
		
		Criteria criteria = new Criteria().andOperator(
				Criteria.where("memberSEQ").is(memberSEQ), 
				Criteria.where("date").gte(dates[0]), 
				Criteria.where("date").lte(dates[1]), 
				Criteria.where("totalExerciseTime").gt(0) 
		);  
		
		Query query = new Query(criteria);   
		
		return mongoTemplate.find(query, RecordDTO.class, "record");
	}
	
	@Override
	public int updateMemberBioByRecord(MemberBioDTO memberBio) {
		return sqlSession.update("com.multi.drd.memberBio.updateByRecord", memberBio);
	}

	@Override
	public int updateGoalByRecord(GoalDTO goal) {
		return sqlSession.update("com.multi.drd.record.updateGoalByRecord", goal);
	}
		
}