package com.multi.drd.cardio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardioServiceImpl implements CardioService {
	private CardioDAO dao;
	public CardioServiceImpl() {}
	@Autowired
	public CardioServiceImpl(CardioDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public CardioDTO findOne(int cardioSEQ) {
		return dao.findOne(cardioSEQ);
	}

}
