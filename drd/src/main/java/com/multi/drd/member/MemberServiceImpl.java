package com.multi.drd.member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.drd.goal.GoalDTO;
import com.multi.drd.memberbio.MemberBioDTO;
import com.multi.drd.memberbio.MemberBioService;
import com.multi.drd.personalroutine.PersonalRoutineDTO;
import com.multi.drd.routine.RoutineDTO;
import com.multi.drd.utils.JsonUtils;

@Service 
public class MemberServiceImpl implements MemberService {
	
	MemberDAO dao;   
	MemberBioService memberBioService;
	
	public MemberServiceImpl() {
		super();
	} 
	
	@Autowired
	public MemberServiceImpl(MemberDAO dao, MemberBioService memberBioService) {
		super();
		this.dao = dao;
		this.memberBioService = memberBioService;
	}

	@Override  
	@Transactional(rollbackFor = Exception.class)
	public int register(MemberDTO registerMember, MemberBioDTO registerMemberBio, PersonalRoutineDTO pRoutine){

		dao.register(registerMember); 
		
		// 가입 후 MemberBio 생성
		int registerMemberSEQ = registerMember.getMemberSEQ();
		registerMemberBio.setMemberBioSEQ(registerMemberSEQ);   
		memberBioService.register(registerMemberBio);
		
		// 가입 후 개인화된 루틴 생성 및 생성된 PersonalRoutine을 Member의 personalRoutineSEQ에 등록
		createPersonalRoutine(pRoutine);
		int pRoutineSEQ = pRoutine.getPersonalRoutineSEQ();
		registerMember.setPersonalRoutineSEQ(pRoutineSEQ);
		updatePersonalRoutineSEQ(registerMemberSEQ, pRoutineSEQ);	
		 	
		// --- 사용자의 신체 정보 및 설정된 루틴 정보를 기반으로 목표 설정
		int result = createGoal(registerMember, registerMemberBio, pRoutine);  
		
		return result;
	}
	
	@Override
	public MemberDTO login(MemberDTO loginMember) {
		
		return dao.login(loginMember);
	} 
	
	@Override
	public MemberDTO findByNickName(String nickName) {
		return dao.findByNickName(nickName);
	}

	@Override
	public MemberDTO findByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	// 회원 가입 전 루틴 조회
	@Override 
	public List<RoutineDTO> findRoutineByRegisterInfo(HashMap<String, Object> param) {
		// 기타 질병일 경우 고혈압과 동일한 루틴을 추천하기 위함.
		if(Integer.parseInt( param.get("disease").toString()) == 3) {
			param.put("disease", "2");
		}
		
		return dao.findRoutineByRegisterInfo(param);
	} 
	
	// 회원 가입 후 선택한 루틴과 동일한 PersonalRoutine 생성
	@Override 
	public int createPersonalRoutine(PersonalRoutineDTO pRoutine) {
		
		return dao.createPersonalRoutine(pRoutine); 
	}    
	
	// 회원 가입 후 member 테이블의 personalRoutineSEQ 업데이트 
	@Override 
	public int updatePersonalRoutineSEQ(int memberSEQ, int pRoutineSEQ) {
		HashMap<String, Integer> param = new HashMap<String, Integer>();
		param.put("memberSEQ", memberSEQ);
		param.put("personalRoutineSEQ", pRoutineSEQ);
		
		return dao.updatePersonalRoutineSEQ(param);
	}
	
	// 회원 가입 후 목표 자동 생성
	@Override 
	public int createGoal(MemberDTO member, MemberBioDTO memberBio, PersonalRoutineDTO pRoutine) {
		GoalDTO goal = new GoalDTO();
		
		int cardioTime = JsonUtils.parseCardioList(pRoutine).getTotalTime();
		int fitnessTime = JsonUtils.parseFitnessList(pRoutine).getTotalTime();
		int age = member.getAge();
		int gender = member.getGender();
		double height = memberBio.getHeight()/100; // m 단위로 전환
		double weight = memberBio.getWeight();	
		double activityLevel = memberBio.getActivityLevel();
		
		// 일단 5분할 운동으로 가정함.
		int goalExerciseTime = (cardioTime+fitnessTime)*5;
		
		// 2015 한국인 영양소 섭취기준 활용 가이드북 22쪽 성인남녀 필요 칼로리에서 발췌
		int goalCalory = 0;
		double rawGoalCalory = 0.0;
		
		// 여성일 경우
		if(gender == 0) { 
			rawGoalCalory = 354-6.91 * age + activityLevel * (9.36 * weight + 726 * height); 
		}  
		// 남성일 경우 
		else {		
			rawGoalCalory = 662-9.53 * age + activityLevel * (15.91 * weight + 539.6 * height);
		}
		
		goalCalory = Integer.parseInt(String.format("%.0f", rawGoalCalory));
		
		
		// 일일 권장 섭취 단백질량은 1kg에 0.8g 으로 가정. 1주일 치 적용
		int goalProtein = Integer.parseInt(String.format("%.0f", weight*0.8)) * 7; 
		
		goal.setMemberSEQ(member.getMemberSEQ());
		goal.setGoalExerciseTime(goalExerciseTime);
		goal.setGoalCalory(goalCalory);
		goal.setGoalProtein(goalProtein);
			
		return dao.createGoal(goal);
	}
	

}
