package edu.cmpe273.univserver.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmpe273.uniserver.util.ServerUtil;

public class RegisterValidatorTest {

	@Test
	public void test() {
		ServerUtil util = new ServerUtil();
		
		assertEquals(true, util.isChar("ABCDEF"));
		assertEquals(true, util.isChar("ancdefg"));
		assertEquals(true, util.isChar("AecbEEBCas"));
		assertEquals(false, util.isChar("ASDH2384728"));
		assertEquals(false, util.isChar("@&#$^"));
		
		assertEquals(true, util.isNumber("12213123"));
		assertEquals(false, util.isNumber("ANC3246ASJA"));
		assertEquals(false, util.isNumber("123DNFD455"));
		assertEquals(false, util.isNumber("^534&*%^"));
		
		assertEquals(true, util.alphaNumeric("123DNFD455"));
		assertEquals(true, util.alphaNumeric("12213123"));
		assertEquals(true, util.alphaNumeric("ASBASD"));
		assertEquals(false, util.alphaNumeric("AS  BASD"));
		assertEquals(false, util.alphaNumeric("%# ^$"));

		assertEquals(true, util.alphaNumericSpaces("123DNFD455"));
		assertEquals(true, util.alphaNumericSpaces("12213123"));
		assertEquals(true, util.alphaNumericSpaces("ASBASD"));
		assertEquals(true, util.alphaNumericSpaces("AS  BASD"));
		assertEquals(false, util.alphaNumericSpaces("%# ^$"));
		
		assertEquals(true, util.isAddress("/#HSGD743485"));
		assertEquals(true, util.isAddress("/#HSGD743/##485"));
		assertEquals(true, util.isAddress("SADASDJH"));
		assertEquals(true, util.isAddress("234324"));
		assertEquals(false, util.isAddress("%# ^$"));
		
		assertEquals(true, util.isValidEmail("234324@344335.com"));
		assertEquals(true, util.isValidEmail("email@email.com"));
		assertEquals(false, util.isValidEmail("email@email$.com"));
		assertEquals(false, util.isValidEmail("email@email.com."));
		
	}

}
