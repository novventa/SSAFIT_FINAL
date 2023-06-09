package com.ssafit.model.dao;

import java.util.List;

import com.ssafit.model.dto.Calendar;

public interface CalendarDao {
	
	public List<Calendar> selectAll(int idx);

	public List<Calendar> selectDate(int idx, String date);
	
	public int insertCalendar(Calendar calendar);
	
	public int deleteCalendar(int idx);
	
	public int updateCalendar(Calendar calendar);
	
}
