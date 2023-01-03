package com.multi.drd.record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RecordDAOImpl implements RecordDAO {
	MongoTemplate mongoTemplate;
	
	@Autowired
	public RecordDAOImpl(MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
	}


	@Override
	public RecordDTO latestRecord(String id) {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria("id");
		criteria.is(id);
		Query query = new Query(criteria);
		query.with(new Sort(new Order(Direction.DESC, "date")));
		return mongoTemplate.findOne(query, RecordDTO.class, "record");
	}
	
	@Override
	public List<RecordDTO> findFoodList(String id) {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria("id");
		criteria.is(id);
		Query query = new Query(criteria);
		List<RecordDTO> docs = mongoTemplate.find(query, RecordDTO.class, "record");
		return docs;
	}


}
