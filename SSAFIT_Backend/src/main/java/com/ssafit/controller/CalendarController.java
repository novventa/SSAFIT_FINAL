package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.exception.CustomException;
import com.ssafit.model.dto.Calendar;
import com.ssafit.model.dto.ErrorCode;
import com.ssafit.model.service.CalendarService;

@RestController
@RequestMapping("/api-calendar")
public class CalendarController {

	private static final String SUCCESS = "success";

	@Autowired
	CalendarService calendarService;

	@PostMapping("add")
	public ResponseEntity<?> calendarAdd(Calendar calendar) throws CustomException {
		int result = calendarService.addCalendar(calendar);

		if (result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}

	@GetMapping("list/{userIdx}")
	public ResponseEntity<?> calendarList(@PathVariable int userIdx) throws CustomException {

		List<Calendar> list = calendarService.findAllCalendars(userIdx);

		if (list == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

		if (list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Calendar>>(list, HttpStatus.OK);
	}

//	@GetMapping("date-list/{idx}/{date}")
//	public ResponseEntity<?> calendarDetails(@PathVariable int idx, @PathVariable String date){
//		
//		List<Calendar> list = calendarService.findCalendarByDate(idx, date);
//		
//		if(list==null || list.size()==0)
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		
//		return new ResponseEntity<List<Calendar>> (list,HttpStatus.OK);
//	}

	@PutMapping("modify")
	public ResponseEntity<?> calendarModify(Calendar calendar) throws CustomException {
		int result = calendarService.modifyCalendar(calendar);

		if (result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@DeleteMapping("remove/{idx}")
	public ResponseEntity<?> calendarRemove(@PathVariable int idx) throws CustomException {
		int result = calendarService.removeCalendar(idx);

		if (result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

}
