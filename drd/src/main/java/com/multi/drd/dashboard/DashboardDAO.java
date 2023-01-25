package com.multi.drd.dashboard;

import java.util.List;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
import com.multi.drd.record.RecordDTO;

public interface DashboardDAO {
	List<AggregationResultDTO> excerciseHourByWeek(int memberSEQ);
	List<AggregationResultDTO> cardioMinByWeek(int memberSEQ);
	List<AggregationResultDTO> fitnessMinByWeek(int memberSEQ);
	List<RecordDTO> findByWeek(int memberSEQ);

}
