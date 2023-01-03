// MongoDB 통신 테스트 용

package com.multi.drd.exercise;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
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
	String MEMBER_ID_MOCK = "1";//id session에서 받아올 값
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
		//날짜 값 받아오기(parameter)
		//변수 형식 변경
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date paradate = null;
			try {
				paradate = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//ID_SEQ(목업)으로 record 검색
		Criteria criteria = new Criteria("memberSEQ");
		criteria.is(Integer.parseInt(MEMBER_ID_MOCK));
		Query query = new Query(criteria);
		List<RecordDTO> test = mongoTemplate.find(query, RecordDTO.class, "record");
		//record exerciselist로 exercise 검색
		List<Integer> recordexercise = test.get(0).getExerciseList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recordexercise",recordexercise);
		List<ExerciseDTO> exercisetest = sqlSession.selectList("com.multi.drd.exercise.test2", map);
		model.addAttribute("fromCalenderDate", paradate);
		model.addAttribute("fromRecordTime", test.get(0).getTotalExerciseHour());
		model.addAttribute("fromRecordExerciselist", exercisetest);
	return "exercise/p9";
	}
}
