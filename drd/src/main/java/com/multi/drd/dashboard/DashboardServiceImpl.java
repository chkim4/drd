package com.multi.drd.dashboard;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
import com.multi.drd.record.RecordDTO;
@Service
public class DashboardServiceImpl implements DashboardService {
	DashboardDAO dao;
	
	@Autowired
	public DashboardServiceImpl(DashboardDAO dao) {
		super();
		this.dao = dao;
	}
	


	public DashboardServiceImpl() {
		super();
	}



	/*
	 * @Override public MemberDTO getMemberInfo(int memberSEQ) { // TODO
	 * Auto-generated method stub return dao.read(memberSEQ); }
	 * 
	 * 
	 * 
	 * @Override public MemberBioDTO getMemberBio(int memberSEQ) { // TODO
	 * Auto-generated method stub return dao.readbio(memberSEQ); }
	 * 
	 */

	@Override
	public List<RecordDTO> ExcerciseHourByWeek(int memberSEQ) {
		// TODO Auto-generated method stub
		return dao.ExcerciseHourByWeek(memberSEQ);
	}

}
