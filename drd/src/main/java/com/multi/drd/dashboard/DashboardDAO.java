package com.multi.drd.dashboard;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;

public interface DashboardDAO {
	public MemberDTO read(String id);
	public MemberBioDTO readbio(String id);
}
