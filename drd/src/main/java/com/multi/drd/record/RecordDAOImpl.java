package com.multi.drd.record;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
	
}