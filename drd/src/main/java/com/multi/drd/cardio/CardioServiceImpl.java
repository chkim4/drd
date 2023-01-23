package com.multi.drd.cardio;

import java.util.List;
import java.util.Map;

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
	@Override
	public List<CardioDTO> findByIntensity(int intensity) {
		return dao.findByIntensity(intensity);
	}
	@Override
	public List<CardioDTO> findByIntensitywithseq(Map<String, Object> map) {
		return dao.findByIntensitywithseq(map);
	}
	@Override
	public List<CardioDTO> findbyname(String name) {
		return dao.findbyname(name);
	}

}
