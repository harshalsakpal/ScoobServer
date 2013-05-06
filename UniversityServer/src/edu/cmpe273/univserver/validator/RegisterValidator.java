package edu.cmpe273.univserver.validator;

import edu.cmpe273.uniserver.util.ServerUtil;
import edu.cmpe273.univserver.beans.Person;

public class RegisterValidator {
	
	public String validateRegisterInput(Person person){
		System.out.println("In Validate Inout");
		ServerUtil util = new ServerUtil();
		
		if( person.getFirstName()==null || !util.isChar(person.getFirstName())){
			return "Invalid First Name";
		}
		
		if(person.getLastName()==null || !util.isChar(person.getLastName())){
			return "Invalid Last Name";
		}
		
		if(person.getDateOfBirth()==null || !util.isChar(person.getLastName())){
			return "Invalid Last Name";
		}
		
		if(person.getAddrLine1()==null || !util.isAddress(person.getAddrLine1())){
			return "Invalid Address Line1";
		}
		
		if(person.getAddrLine2()==null || !util.isAddress(person.getAddrLine2())){
			return "Invalid Address Line2";
		}
		
		if(person.getEmailid()==null || !util.isValidEmail(person.getEmailid())){
			return "Invalid Email id";
		}
		
		if(person.getCityName()==null || !util.alphaNumericSpaces(person.getCityName())){
			return "Invalid City Name";
		}
		
		if(person.getZipCode()==null || !util.isNumber(person.getZipCode())){
			return "Invalid Zip Codes";
		}
		
		return "SUCCESS";
	}

	
	
}
