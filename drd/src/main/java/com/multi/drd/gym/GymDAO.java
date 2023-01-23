package com.multi.drd.gym;



public interface GymDAO {
	
GymDTO readGym(int memberSEQ);

int updateGym(GymDTO gym);

int deleteGym(int memberSEQ);

int registerGym(GymDTO gym);
	
	

}
