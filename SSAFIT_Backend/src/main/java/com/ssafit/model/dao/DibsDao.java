package com.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.model.dto.Dibs;

@Repository
public interface DibsDao {
	List<Dibs> selectAll(String nickname);
	
	Dibs selectDibs(Dibs dibs);
	
	int insertDibs(Dibs dibs);
	
	int deleteDibs(Dibs dibs);
}
