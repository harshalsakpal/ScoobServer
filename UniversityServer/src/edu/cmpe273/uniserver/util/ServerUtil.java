package edu.cmpe273.uniserver.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


public class ServerUtil {

	public static final String regexString = "^[A-Za-z]*";
	public static final String regexNumber = "^[0-9]*";
	public static final String regexAlphaNumeric = "^[A-Za-z0-9]*";
	public static final String regexAlphaNumericSpaces = "^[A-Za-z0-9 ]*";
	public static final String regexDate = "([0-9]{2})\\([0-9]{2})\\([0-9]{4})";
	public static final String regexAddress = "^[A-Za-z0-9 #/]*";
	public static final String regexEmail = "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+.([a-zA-Z])+([a-zA-Z])+";
	

	public boolean isChar(String input) {
		return Pattern.matches(regexString, input);
	}

	public boolean isNumber(String input) {
		return Pattern.matches(regexNumber, input);
	}

	public boolean alphaNumeric(String input) {
		return Pattern.matches(regexAlphaNumeric, input);
	}

	public boolean alphaNumericSpaces(String input) {
		return Pattern.matches(regexAlphaNumericSpaces, input);
	}

	public boolean isAddress(String input) {
		return Pattern.matches(regexAddress, input);
	}

	public boolean isValidEmail(String input) {
		return Pattern.matches(regexEmail, input);
	}
	
	public boolean isValidDateFormat(String input) {
		boolean isValid = false;
		if(Pattern.matches(regexDate, input) && isValidDate(input))
			isValid = true;
		return isValid;
	}

	public boolean isValidDate(String stringDateFormat) {
		boolean isvalid = false;
		DateFormat formatter = null;
		Date convertedDate = null;
		Calendar cal  = Calendar.getInstance();

		formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			convertedDate = (Date) formatter.parse(stringDateFormat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Date from MM/dd/yyyy String in Java : "+ convertedDate);
		
		String expDateString = formatter.format(cal.getTime());

		 Date today = new Date(expDateString);
		 System.out.println("Today is " + today);
		 
		 if(convertedDate.after(today) || convertedDate.equals(today))
			 isvalid = false;
		 else
			 isvalid = true;

		return isvalid;
	}


}
