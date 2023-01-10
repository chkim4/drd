package com.multi.drd.record;

import java.util.List;

public interface RecordService {
	public RecordDTO latestRecord(String id);
	public List<RecordDTO> findFoodList(String id); 
	
	// 테스트를 위해 추가
	public RecordDTO latestRecord(int memberSEQ);
	public List<RecordDTO> findFoodList(int memberSEQ); 
	
}
