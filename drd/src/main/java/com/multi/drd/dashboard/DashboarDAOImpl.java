package com.multi.drd.dashboard;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
import com.multi.drd.record.RecordDTO;
import com.multi.drd.utils.DateUtils;
@Repository
public class DashboarDAOImpl implements DashboardDAO {
	SqlSession sqlSession;
	MongoTemplate mongoTemplate;
	
	@Autowired
	public DashboarDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}
	


	public DashboarDAOImpl() {
		super();
	}



	/*
	 * @Override public MemberDTO read(int memberSEQ) { // TODO Auto-generated
	 * method stub return sqlSession.selectOne("com.multi.drd.dashboard.read",
	 * memberSEQ); }
	 * 
	 * 
	 * 
	 * @Override public MemberBioDTO readbio(int memberSEQ) { // TODO Auto-generated
	 * method stub return sqlSession.selectOne("com.multi.drd.dashboard.readbio",
	 * memberSEQ); }
	 */
	@Override
	public List<RecordDTO> ExcerciseHourByWeek(int memberSEQ){
		/*MatchOperation matchOperation= Aggregation.match( 
		        new Criteria().andOperator(
		        		 Criteria.where("date").gte(DateUtils.getISODate("2022-12-01","start"))
			              .lte(DateUtils.getISODate("2022-12-31", "end")),
		            Criteria.where("memberSEQ").is(memberSEQ)
				)
		);
//		주별로 totalExerciseTime 합해야 하는데 이렇게하면 일별로..?
		GroupOperation groupByWeek = Aggregation.group(DateOperators.Week)
				  .sum("totalExerciseTime").as("totalExerciseTimeofWeek");
		  
		SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "_id");

			Aggregation aggregation = Aggregation.newAggregation(
				groupByWeek, sortOperation, matchOperation
			);
			
			mongoTemplate.aggregate(aggregation, "record", RecordDTO.class);
			
			AggregationResults<RecordDTO> docs = mongoTemplate.aggregate(aggregation, "record", RecordDTO.class);
			List<RecordDTO> dataList = docs.getMappedResults();
			System.out.println("dataList" + dataList);
		return dataList;*/
		return null;
		
	}

}
