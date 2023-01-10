///*
// * 참고 
// * https://docs.spring.io/spring-data/mongodb/docs/3.0.4.RELEASE/reference/html/#repositories.query-methods.details
// * https://truth1018.tistory.com/12
// */
//
//package com.multi.drd.record;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//public interface RecordRepository extends MongoRepository<RecordDTO, Integer>{
//	
//	List<RecordDTO> findByMemberSEQ(int memberSEQ);
//	
//	List<RecordDTO> findByMemberSEQAndMonthlyDate(int memberSEQ, Date startDate, Date endDate);
//}
