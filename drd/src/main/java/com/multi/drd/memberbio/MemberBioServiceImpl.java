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
		
		return dao.register(registerMemberBio);
	} 
	
	@Override
	public MemberBioDTO findByPK(int memberSEQ) {
		// TODO Auto-generated method stub
		return dao.findByPK(memberSEQ);
	}

}
