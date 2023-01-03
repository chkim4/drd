package com.multi.drd.dashboard;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;

public interface DashboardService {
	MemberDTO getMemberInfo(String id);
	MemberBioDTO getMemberBio(String id);
}
