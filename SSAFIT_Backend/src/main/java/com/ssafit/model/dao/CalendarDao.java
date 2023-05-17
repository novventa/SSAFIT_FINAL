package com.ssafit.model.dao;

import java.util.Date;
import java.util.List;

import com.ssafit.model.dto.Calendar;

public interface CalendarDao {

	public List<Calendar> selectDate(String nickname, Date date);
	
	public List<Calendar> selectAll(String nickname);
	
	public int insertCalendar(Calendar calendar);
	
	public int deleteCalendar(int idx);
	
	public int updateCalendar(Calendar calendar);
	
}
