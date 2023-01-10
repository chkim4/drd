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

//	최근트레이닝 기록 조회 =>컬렉션 제목 필요
	@Override
	public RecordDTO latestRecord(int memberSEQ) {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria("memberSEQ");
		criteria.is(memberSEQ);
		Query query = new Query(criteria);
		query.with(new Sort(new Order(Direction.DESC, "date")));
		
		return mongoTemplate.findOne(query, RecordDTO.class, "record");
	}
	
	@Override
	public List<RecordDTO>findFoodList(int memberSEQ) { 
		
//		Query query = new Query(
//				  Criteria.where("memberSEQ").is(memberSEQ)
//				  .andOperator(
//			                Criteria.where("date").gte(DateUtils.getISODate("2023-01-01","start")),
//			                Criteria.where("date").lte(DateUtils.getISODate("2023-01-31", "end"))
//						)
//				); 
		
		Date[] test = null;
		
		try {
			test = DateUtils.getMonthlyISODate(2022, 12);
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Query query = new Query(
				  Criteria.where("memberSEQ").is(memberSEQ)
				  .andOperator(
			                Criteria.where("date").gte(test[0]),
			                Criteria.where("date").lte(test[1])
						)
				);
		List<RecordDTO> docs =  mongoTemplate.find(query, RecordDTO.class, "record"); 
//		System.out.println("docs[0].exerciseList: " + docs.get(0).exerciseList);
	
		return docs;
	}
	
	@Override
	public RecordDTO latestRecord(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<RecordDTO> findFoodList(String id) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}