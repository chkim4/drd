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
	public int register(MemberBioDTO registerMemberBio, int gender) {
		double[] activityLevelWoman = new double[] {1.0, 1.12, 1.27, 1.45}; // 여성의 ActivityLevel 값 
		double[] activityLevelMan = new double[] {1.0, 1.11, 1.25, 1.48}; // 남성의 ActivityLevel 값
		
		// 2015 한국인영양소 섭취기준 활용 기준 신체 활동 지수 설정 
		if(gender == 0) {
			registerMemberBio.setActivityLevel(activityLevelWoman[(int)registerMemberBio.getActivityLevel()]);
		} 
		else {
			registerMemberBio.setActivityLevel(activityLevelMan[(int)registerMemberBio.getActivityLevel()]);
		}
		
		return dao.register(registerMemberBio); 
	} 
	
	@Override
	public MemberBioDTO findByPK(int memberSEQ) {
		// TODO Auto-generated method stub
		return dao.findByPK(memberSEQ);
	}

}
