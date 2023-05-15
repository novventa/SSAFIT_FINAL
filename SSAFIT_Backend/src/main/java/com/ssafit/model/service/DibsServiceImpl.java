package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.DibsDao;
import com.ssafit.model.dto.Dibs;

@Service
public class DibsServiceImpl implements DibsService {

	@Autowired
	DibsDao dibsDao;
	
	@Override
	public List<Dibs> getDibsList(String nickname) {
		return dibsDao.selectAll(nickname);
	}
	
	@Override
	public Dibs getDibs(Dibs dibs) {
		return dibsDao.selectDibs(dibs);
	}

	@Override
	public int addDibs(Dibs dibs) {
		return dibsDao.insertDibs(dibs);
	}

	@Override
	public int removeDibs(Dibs dibs) {
		return dibsDao.deleteDibs(dibs);
	}


}
