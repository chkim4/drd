package com.multi.drd.dashboard;

import java.util.List;

import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
import com.multi.drd.record.RecordDTO;

public interface DashboardService {
	//월간 주단위 운동시간
	List<AggregationResultDTO> excerciseHourByWeek(int memberSEQ);
	//일주일간 운동기록
	List<RecordDTO> findByWeek(int memberSEQ);
	//월간 주단위 유산소 운동시간
	List<AggregationResultDTO> cardioMinByWeek(int memberSEQ);
	//월간 주단위 무산소 운동시간
	List<AggregationResultDTO> fitnessMinByWeek(int memberSEQ);
}
