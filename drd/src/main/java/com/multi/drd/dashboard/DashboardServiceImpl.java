package com.multi.drd.dashboard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
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



	@Override
	public MemberDTO getMemberInfo(String id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}



	@Override
	public MemberBioDTO getMemberBio(String id) {
		// TODO Auto-generated method stub
		return dao.readbio(id);
	}

}
