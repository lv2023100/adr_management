package com.adrmanagement.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProtoConverter {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

	  public static String toProto(LocalDateTime localDateTime) {
	    String formattedDateTime = localDateTime.format(formatter);
	    return formattedDateTime;
	  }

	  public static LocalDateTime fromProto(String timeString) {
	    return LocalDateTime.parse(timeString, formatter);
	  }
	  
	  public static String s(String s) {
		  return s == null ?"":s;
	  }
}
