package com.multi.drd.record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
	RecordDAO dao; 
	
	@Autowired
	public RecordServiceImpl(RecordDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public RecordDTO findLatestRecord(int memberSEQ) {
		return dao.findLatestRecord(memberSEQ);
	}

	@Override
	public RecordDTO findTodayRecord(int memberSEQ) {
		return dao.findTodayRecord(memberSEQ);
	}

	@Override
	public List<RecordDTO> findMonthlyRecord(int memberSEQ, int year, int month) {
		return dao.findMonthlyRecord(memberSEQ, year, month);
	}

	@Override
	public int updateCardio(RecordDTO record) {
		
		RecordDTO originalRecord =  dao.findDailyRecord(record.getMemberSEQ(), record.getDate());
		
		// 총 운동 시간: (원래 총 운동 시간 - 원래 유산소 운동 시간) + 업데이트 된 유산소 운동 시간 
		int exerciseTotalTime = originalRecord.getTotalExerciseTime() 
									- originalRecord.getCardioObj().getTotalTime() + record.getCardioObj().getTotalTime(); 
		
		
		record.setTotalExerciseTime(exerciseTotalTime);
		
		return dao.updateCardio(record);
	}
}
