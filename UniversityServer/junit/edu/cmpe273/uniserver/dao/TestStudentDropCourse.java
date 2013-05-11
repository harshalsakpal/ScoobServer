package edu.cmpe273.uniserver.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmpe273.univserver.dao.Student_drop;

public class TestStudentDropCourse {
	
	Student_drop sd = new Student_drop();
	
	@Test
	public void testDropCourse() {
		assertEquals("Dropped Successfully", sd.dropCourse("49", "CMPE273", "2"));
	}
	
	@Test
	public void testDropInvalidCourse() {
		assertEquals("Drop Unsuccessfull", sd.dropCourse("49", "CMPE273", "2"));
	}

}
