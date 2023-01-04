package com.multi.drd.routine;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.drd.member.MemberDTO;

@Repository
public class RoutineDAOImpl implements RoutineDAO { 
	
	SqlSession sqlSession; 
	
	public RoutineDAOImpl() {
		super();
	} 
	
	@Autowired
	public RoutineDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public List<RoutineDTO> findByRegisterInfo(HashMap<String,Object> param) { 
		
		//BMI 추가 		
		double weight = Double.parseDouble(param.get("weight").toString());
		double height = Double.parseDouble(param.get("height").toString()); 
		double heightInMeter = height/100;
		
		String bmi = String.format("%.1f", weight/(heightInMeter*heightInMeter));
		
		param.put("bmi", bmi); 
		
		System.out.println("param in DAO: "+ param);
		
		return sqlSession.selectList("com.multi.drd.routine.findByRegisterInfo", param);
	}

}
