package com.ssafit.model.service;

import java.util.List;

import com.ssafit.model.dto.Calendar;

public interface CalendarService {

	public List<Calendar> findCalendarByDate(int idx, String date);
	
	public List<Calendar> findAllCalendars(int idx);
	
	public int addCalendar(Calendar calendar);
	
	public int modifyCalendar(Calendar calendar);
	
	public int removeCalendar(int idx);
	
}
