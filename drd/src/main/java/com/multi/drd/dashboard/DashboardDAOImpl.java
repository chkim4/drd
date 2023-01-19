package com.multi.drd.dashboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.AggregationOptions;
import com.mongodb.AggregationOptions.OutputMode;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.multi.drd.record.RecordDTO;
import com.multi.drd.utils.DateUtils;
@Repository
public class DashboardDAOImpl implements DashboardDAO {
	SqlSession sqlSession;
	MongoTemplate mongoTemplate;
	
	@Autowired
	public DashboardDAOImpl(SqlSession sqlSession, MongoTemplate mongoTemplate) {
		super();
		this.sqlSession = sqlSession;
		this.mongoTemplate = mongoTemplate;
	}
	
	public DashboardDAOImpl() {
		super();
	}

	@Override
	public List<AggregationResultDTO> excerciseHourByWeek(int memberSEQ){
		
		List<DBObject> list = new ArrayList<DBObject>();
		ProjectionOperation dateProjection = Aggregation.project()
		.and("date").extractWeek().as("week")
		.and("totalExerciseTime").as("totalExerciseTime")
		.and("1").as("1");
		
		GroupOperation groupby = Aggregation
				.group("1","week").sum("totalExerciseTime").as("totalExerciseTime");
		
		
		MatchOperation where = Aggregation.match( 
	        new Criteria().andOperator(
	        	Criteria.where("date").gte(DateUtils.getISODate("2023-01-01","start"))
	              .lte(DateUtils.getISODate("2023-01-31", "end")),
	            Criteria.where("memberSEQ").is(memberSEQ)
		)	);  //조건절
		SortOperation sort = Aggregation.sort(Sort.Direction.ASC, "_id");
				
		list.add(where.toDBObject(Aggregation.DEFAULT_CONTEXT));
		list.add(dateProjection.toDBObject(Aggregation.DEFAULT_CONTEXT));
		list.add(groupby.toDBObject(Aggregation.DEFAULT_CONTEXT));
		list.add(sort.toDBObject(Aggregation.DEFAULT_CONTEXT));
		


		DBCollection col = mongoTemplate.getCollection("record");

		Cursor cursor = 
				col.aggregate(list, AggregationOptions.builder().allowDiskUse(true).outputMode(OutputMode.CURSOR).build());

		List<AggregationResultDTO> result = new ArrayList<AggregationResultDTO>();
		
		while(cursor.hasNext()) {
		     DBObject object = cursor.next();
		     System.out.println("dbobject"+object);

		     result.add(new AggregationResultDTO(object.get("_id").toString(),object.get("totalExerciseTime").toString()));
		}
		System.out.println("result in dao" + result);
		return result;
		
	}



	@Override
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
			                //Criteria.where("date").gte(DateUtils.getDailyISODate(stDate)[0]),
			                //Criteria.where("date").lte(DateUtils.getDailyISODate(today)[1])
						  	Criteria.where("date").gte(DateUtils.getISODate("2023-01-10","start")),
			               Criteria.where("date").lte(DateUtils.getISODate("2023-01-17","end"))
			                
						)
				);
		List<RecordDTO> docs =  mongoTemplate.find(query, RecordDTO.class, "record");
		System.out.println("in dao findByWeek" + docs);
		return docs;
	}

	@Override
	public List<AggregationResultDTO> cardioMinByWeek(int memberSEQ) {
		// TODO Auto-generated method stub
		List<DBObject> list = new ArrayList<DBObject>();
		UnwindOperation unwind = Aggregation.unwind("cardioObj");
		ProjectionOperation dateProjection = Aggregation.project()
		.and("date").extractWeek().as("week")
		.and("cardioObj.totalTime").as("totalExerciseTime")
		.and("1").as("1");
		
		

		GroupOperation groupby = Aggregation.group("1","week").sum("totalExerciseTime").as("totalExerciseTime");
		
		MatchOperation where = Aggregation.match( 
	        new Criteria().andOperator(
	        	Criteria.where("date").gte(DateUtils.getISODate("2023-01-01","start"))
	              .lte(DateUtils.getISODate("2023-01-31", "end")),
	            Criteria.where("memberSEQ").is(memberSEQ)
		)	);  //조건절
		
		SortOperation sort = Aggregation.sort(Sort.Direction.ASC, "_id");
		
		list.add(unwind.toDBObject(Aggregation.DEFAULT_CONTEXT));
		list.add(where.toDBObject(Aggregation.DEFAULT_CONTEXT));
		list.add(dateProjection.toDBObject(Aggregation.DEFAULT_CONTEXT));
		list.add(groupby.toDBObject(Aggregation.DEFAULT_CONTEXT));
		list.add(sort.toDBObject(Aggregation.DEFAULT_CONTEXT));

		


		DBCollection col = mongoTemplate.getCollection("record");

		Cursor cursor = 
				col.aggregate(list, AggregationOptions.builder().allowDiskUse(true).outputMode(OutputMode.CURSOR).build());

		List<AggregationResultDTO> result = new ArrayList<AggregationResultDTO>();
		
		while(cursor.hasNext()) {
		     DBObject object = cursor.next();
		     System.out.println("dbobject"+object);

		     result.add(new AggregationResultDTO(object.get("_id").toString(),object.get("totalExerciseTime").toString()));
		}
		System.out.println("result in dao" + result);
		return result;
		
	}

	@Override
	public List<AggregationResultDTO> fitnessMinByWeek(int memberSEQ) {
		// TODO Auto-generated method stub
		List<DBObject> list = new ArrayList<DBObject>();
		UnwindOperation unwind = Aggregation.unwind("fitnessObj");
		ProjectionOperation dateProjection = Aggregation.project()
		.and("date").extractWeek().as("week")
		.and("fitnessObj.totalTime").as("totalExerciseTime")
		.and("1").as("1");
		
		

		GroupOperation groupby = Aggregation.group("1","week").sum("totalExerciseTime").as("totalExerciseTime");
		
		MatchOperation where = Aggregation.match( 
	        new Criteria().andOperator(
	        	Criteria.where("date").gte(DateUtils.getISODate("2023-01-01","start"))
	              .lte(DateUtils.getISODate("2023-01-31", "end")),
	            Criteria.where("memberSEQ").is(memberSEQ)
		)	);  //조건절
		
		SortOperation sort = Aggregation.sort(Sort.Direction.ASC, "_id");
		
		list.add(unwind.toDBObject(Aggregation.DEFAULT_CONTEXT));
		list.add(where.toDBObject(Aggregation.DEFAULT_CONTEXT));
		list.add(dateProjection.toDBObject(Aggregation.DEFAULT_CONTEXT));
		list.add(groupby.toDBObject(Aggregation.DEFAULT_CONTEXT));
		list.add(sort.toDBObject(Aggregation.DEFAULT_CONTEXT));



		DBCollection col = mongoTemplate.getCollection("record");

		Cursor cursor = 
				col.aggregate(list, AggregationOptions.builder().allowDiskUse(true).outputMode(OutputMode.CURSOR).build());

		List<AggregationResultDTO> result = new ArrayList<AggregationResultDTO>();
		
		while(cursor.hasNext()) {
		     DBObject object = cursor.next();
		     System.out.println("dbobject"+object);

		     result.add(new AggregationResultDTO(object.get("_id").toString(),object.get("totalExerciseTime").toString()));
		}
		System.out.println("result in dao" + result);
		return result;
	}

}
