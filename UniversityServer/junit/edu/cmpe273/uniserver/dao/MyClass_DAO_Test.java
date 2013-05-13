package edu.cmpe273.uniserver.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import edu.cmpe273.univserver.beans.InstructorCourse;
import edu.cmpe273.univserver.dao.MyClass;

public class MyClass_DAO_Test {
	 InstructorCourse ic = new InstructorCourse();
	 InstructorCourse[] p = null;
	 MyClass i= new MyClass();

	@Test
	public void getInstClassTest() {
	
		assertNotNull(i.getInstClass("43"));
		
		
		
		
	}
	@Test
	public void negativegetInstClassTest() {
		
		assertNull(i.getInstClass("123"));
		
		
		
		
	}

}
