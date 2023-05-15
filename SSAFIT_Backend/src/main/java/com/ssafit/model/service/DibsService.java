package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Dibs;

public interface DibsService {
	List<Dibs> getDibsList(String nickname);

	Dibs getDibs(Dibs dibs);
	
	int addDibs(Dibs dibs);

	int removeDibs(Dibs dibs);
}
