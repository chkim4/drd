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
	public RecordDTO latestRecord(String id) {
		// TODO Auto-generated method stub
		return dao.latestRecord(id);
	}

	@Override
	public List<RecordDTO> findFoodList(String id) {
		// TODO Auto-generated method stub
		return dao.findFoodList(id);
	}

	// 테스트용
	@Override
	public RecordDTO latestRecord(int memberSEQ) {
		// TODO Auto-generated method stub
		return dao.latestRecord(memberSEQ);
	}
	
	@Override
	public List<RecordDTO> findFoodList(int memberSEQ) {
		// TODO Auto-generated method stub
		return dao.findFoodList(memberSEQ);
	}
}
