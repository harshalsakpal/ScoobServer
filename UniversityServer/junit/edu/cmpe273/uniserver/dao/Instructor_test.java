package edu.cmpe273.uniserver.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmpe273.univserver.beans.InstructorCourse;
import edu.cmpe273.univserver.dao.InstructorCourseDAO;

public class Instructor_test {
	InstructorCourseDAO i= new InstructorCourseDAO();
	
		@Test public void Assign_course(){
			 InstructorCourse ic = new InstructorCourse();
			 
			 ic.setCourseNumber("cmpe206");
			 ic.setAvailableSeats("23");
			 ic.setDay("Tue");
			 ic.setDepartment("cmpe");
			 ic.setFilledSeats("34");
			 ic.setLocation("eng345");
			 ic.setSection("1");
			 ic.setSjsuid("11");
			 ic.setTime("4:00-6:00");
			 assertEquals("Course Assigned Successfully",i.AssignCourse(ic));
			 
	}
		
		@Test public void Assign_Invalid_course(){
			 InstructorCourse ic = new InstructorCourse();
			 
			 ic.setCourseNumber("cmpe206");
			 ic.setAvailableSeats("23");
			 ic.setDay("Tue");
			 ic.setDepartment("cmpe");
			 ic.setFilledSeats("34");
			 ic.setLocation("eng345");
			 ic.setSection("1");
			 ic.setSjsuid("11");
			 ic.setTime("4:00-6:00");
			 assertEquals("Course Already Assigned to an Instructor",i.AssignCourse(ic));
			 
	}

}
