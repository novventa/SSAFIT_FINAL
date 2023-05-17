package com.ssafit.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.model.dao.CalendarDao;
import com.ssafit.model.dto.Calendar;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	CalendarDao calendarDao;
	
	@Override
	public List<Calendar> getCalendarByDate(String nickname, Date date) {
		return calendarDao.selectDate(nickname, date);
	}

	@Override
	public List<Calendar> getAllCalendar(String nickname) {
		return calendarDao.selectAll(nickname);
	}

	@Override
	public int addCalendar(Calendar calendar) {
		return calendarDao.insertCalendar(calendar);
	}

	@Override
	public int modifyCalendar(Calendar calendar) {
		return calendarDao.updateCalendar(calendar);
	}

	@Override
	public int removeCalendar(int idx) {
		return calendarDao.deleteCalendar(idx);
	}

}
