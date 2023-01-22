package com.multi.drd.record;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.multi.drd.utils.DateUtils;

@Repository
public class RecordDAOImpl implements RecordDAO {
	MongoTemplate mongoTemplate;
	
	@Autowired
	public RecordDAOImpl(MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
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
	public int deleteField(RecordDTO record, String field) {
		Query query = new Query();
		Update update = new Update();
		 
	    // where절 조건 
		Date[] dates = DateUtils.getDailyISODate(record.getDate()); 
		
	    query.addCriteria(Criteria.where("memberSEQ").is(record.getMemberSEQ()));
	    query.addCriteria(Criteria.where("date").gte(dates[0]).lte(dates[1]));  
	    
	    update.unset(field);
	    
	    return mongoTemplate.updateFirst(query, update, "record").getN();
	}
	
}