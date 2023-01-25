package com.multi.drd.gym;

import java.util.HashMap;

public interface GymService {
	//헬스장 등록
	int insertGym(GymDTO gym);
	//헬스장 데이터 검색
	GymDTO findByGymName(String name);
	//멤버테이블의 gymSEQ 업데이트
	int updateGym(HashMap<String, Integer> param);
	//헬스장 정보
	GymDTO readGym(int memberSEQ);
	//등록한 헬스장 삭제
	int deleteGym(int memberSEQ);
}
