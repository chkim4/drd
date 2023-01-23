package com.multi.drd.gym;

public interface GymService {
	GymDTO readGym(int memberSEQ);

	int updateGym(GymDTO gym);

	int deleteGym(int memberSEQ);

	int registerGym(GymDTO gym);
}
