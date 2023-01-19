package com.multi.drd.memberbio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberBioServiceImpl implements MemberBioService {
	
	MemberBioDAO dao;
	public MemberBioServiceImpl() {
		super();
	}
	
	@Autowired
	public MemberBioServiceImpl(MemberBioDAO dao) {
		super();
		this.dao = dao;
	} 
	
	@Override
	public int register(MemberBioDTO registerMemberBio) {
		// 2015 한국인영양소 섭취기준 활용 기준 신체 활동 지수 설정
		switch((int)registerMemberBio.getActivityLevel()) {
			
			case 0:
				registerMemberBio.setActivityLevel(1.0); // 일주일 0회 운동: PA: 1.0 으로 설정. 
				break;
			case 1:
				registerMemberBio.setActivityLevel(1.11); // 일주일 1~2회 운동: PA: 1.11로 설정 
				break;			
			case 2:
				registerMemberBio.setActivityLevel(1.25); // 일주일 3~5회 운동: PA: 1.25 으로 설정 
				break;
			case 3:
				registerMemberBio.setActivityLevel(1.48); // 일주일 6회 이상 운동: PA: 1.48로 설정 
				break; 
				
			default: 
				registerMemberBio.setActivityLevel(1.0);
				break;
		
		} 
		return dao.register(registerMemberBio); 
		// throw new RuntimeException(); // 트랜잭션 테스트용
	} 
	
	@Override
	public MemberBioDTO findByPK(int memberSEQ) {
		// TODO Auto-generated method stub
		return dao.findByPK(memberSEQ);
	}

}
