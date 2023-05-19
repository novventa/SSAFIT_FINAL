package com.ssafit.model.service;

import java.util.Date;
import java.util.List;

import com.ssafit.model.dto.Calendar;

public interface CalendarService {

	public List<Calendar> findCalendarByDate(String nickname, Date date);
	public List<Calendar> findAllCalendars(String nickname);
	public int addCalendar(Calendar calendar);
	public int modifyCalendar(Calendar calendar);
	public int removeCalendar(int idx);
	
}
