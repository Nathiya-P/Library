package com.library.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

public final class Date {

	private final static String DATE_INPUT_PATTERN = "dd/MM/yyyy";
	private final static String DATE_OUTPUT_PATTERN = "dd-MMM-yyyy";
	
	private final static DateTimeFormatter DATE_INPUT_FORMATTER = DateTimeFormatter.ofPattern(DATE_INPUT_PATTERN);
	private final static DateTimeFormatter DATE_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(DATE_OUTPUT_PATTERN);
	
	public static LocalDate toDate(String date) {
		if(StringUtils.isEmpty(date)) {
			return null;
		}
		
		return LocalDate.parse(date, DATE_INPUT_FORMATTER);
	}
}
