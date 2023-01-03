package com.multi.drd.record;

import java.util.List;

public interface RecordSerivice {
	public RecordDTO latestRecord(String id);
	public List<RecordDTO> findFoodList(String id);
}
