package com.multi.drd.routine;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	
	// 모든 루틴 조회. 회원 가입 시 '전체 보기'를 눌렀을 때 등에 사용 
	@Override
	public List<RoutineDTO> findAll() { 
				
		return sqlSession.selectList("com.multi.drd.routine.findAll");
	}
	
	// 조회된 추천 루틴이 없을 경우 3개의 루틴 반환 
	@Override
	public List<RoutineDTO> findByNoInfo() { 
				
		return sqlSession.selectList("com.multi.drd.routine.findByNoInfo");
	}

	@Override
	public List<RoutineDTO> findByRegisterInfo(HashMap<String,Object> param) { 
		
		//BMI 추가 		
		double weight = Double.parseDouble(param.get("weight").toString());
		double height = Double.parseDouble(param.get("height").toString()); 
		double heightInMeter = height/100;
		
		String bmi = String.format("%.1f", weight/(heightInMeter*heightInMeter));
		
		param.put("bmi", bmi);  
		
		List<RoutineDTO> routineList = sqlSession.selectList("com.multi.drd.routine.findByRegisterInfo", param); 
		
		// 조회된 추천 루틴이 없을 경우 
		if(routineList.isEmpty()) {
			routineList = sqlSession.selectList("com.multi.drd.routine.findByNoInfo");
		} 
		
		return routineList;
	}
	
	public RoutineDTO findBySEQ(int RoutineSEQ) {
		
		return sqlSession.selectOne("com.multi.drd.routine.findOneBySEQ", RoutineSEQ);
	}
}
