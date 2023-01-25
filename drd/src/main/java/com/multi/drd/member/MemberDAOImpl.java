package com.multi.drd.member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.drd.goal.GoalDTO;
import com.multi.drd.personalroutine.PersonalRoutineDTO;
import com.multi.drd.routine.RoutineDTO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	SqlSession sqlSession;
	
	public MemberDAOImpl() {
		
	}
		
	@Autowired
	public MemberDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	} 
	
	@Override
	public int register(MemberDTO registerMember) {
		
		return sqlSession.insert("com.multi.drd.member.register",registerMember);
	} 
	
	@Override
	public MemberDTO login(MemberDTO loginMember) {
		
		return sqlSession.selectOne("com.multi.drd.member.login",loginMember);
	}

	@Override
	public MemberDTO findByNickName(String nickName) {
		
		return sqlSession.selectOne("com.multi.drd.member.findByNickName", nickName);
	}

	@Override
	public MemberDTO findByEmail(String email) {
		return sqlSession.selectOne("com.multi.drd.member.findByEmail", email);
	} 
	
	@Override
	public MemberDTO findByPK(int memberSEQ) {
		return sqlSession.selectOne("com.multi.drd.member.findByPK", memberSEQ);
	}
 
	// 일단 질병 정보에 따른 루틴 추천으로 구현. 추 후 확장을 위해 리스트로 반환
	@Override
	public List<RoutineDTO> findRoutineByRegisterInfo(HashMap<String, Object> param) {
		return sqlSession.selectList("com.multi.drd.member.findRoutineByRegisterInfo", param);
	}

	@Override
	public int createPersonalRoutine(PersonalRoutineDTO pRoutine) {
		return sqlSession.insert("com.multi.drd.member.createPersonalRoutine", pRoutine);
	}

	@Override
	public int updatePersonalRoutineSEQ(HashMap<String, Integer> param) { 
		
		return sqlSession.update("com.multi.drd.member.updatePersonalRoutineSEQ", param); 
	}

	@Override
	public int createGoal(GoalDTO goal) {
		return sqlSession.insert("com.multi.drd.member.createGoal", goal);
	}

}
