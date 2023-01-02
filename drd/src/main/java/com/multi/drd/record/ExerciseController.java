// MongoDB 통신 테스트 용

package com.multi.drd.record;


import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.multi.drd.record.RecordDTO;

@Controller 
public class ExerciseController {
	String MEMBER_ID_MOCK = "63aa8e6b739891db475bb500";//id session에서 받아올 값
	String FROM_CALENDER_DATE_MOCK = "2022-12-26";//param으로 받을 값
	Date TEST_DATE_MOCK = new Date(2022,12,26);
//	String[] FROM_RECORD_EXERCISELIST = {"exercise1","exercise2"};//record와 exercise에서 받아올 값
	//
	SqlSession sqlSession;
	MongoTemplate mongoTemplate;
	
	public ExerciseController() {};
	@Autowired
	public ExerciseController(SqlSession sqlSession,MongoTemplate mongoTemplate) {
			super();
			this.mongoTemplate = mongoTemplate;
			this.sqlSession = sqlSession;
		}


@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String set(Model model, String date ) {
		
		List<RecordDTO> test = mongoTemplate.findAll(RecordDTO.class, "record");
		Criteria criteria = new Criteria("memberSEQ");
		System.out.println(test);
		
//		criteria.is(MEMBER_ID_MOCK);
//		Query query = new Query(criteria);
//		query.addCriteria(Criteria.where("memberSEQ").is(MEMBER_ID_MOCK));
//		List<RecordDTO> test1 = mongoTemplate.find(query, RecordDTO.class, "record");
//		
//		System.out.println(test1.get(0).getExerciseList().get(0));
//		
//		model.addAttribute("memberId", MEMBER_ID_MOCK);
//		model.addAttribute("fromCalenderDate", pardate);
//		model.addAttribute("fromRecordTime", test1.get(0).getTotalExerciseHour());
//		model.addAttribute("fromRecordExerciselist", exercisetest);
		return "sample/blank"; 
	}
}
