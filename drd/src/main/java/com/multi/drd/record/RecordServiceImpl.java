package com.multi.drd.record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordSerivice {
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

}
