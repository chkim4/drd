package com.multi.drd.dashboard;

import java.util.List;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
import com.multi.drd.record.RecordDTO;

public interface DashboardService {
	/*
	 * MemberDTO getMemberInfo(int memberSEQ); MemberBioDTO getMemberBio(int
	 * memberSEQ);
	 */
	List<RecordDTO> ExcerciseHourByWeek(int memberSEQ);
}
