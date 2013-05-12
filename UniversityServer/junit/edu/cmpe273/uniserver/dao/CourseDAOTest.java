package edu.cmpe273.uniserver.dao;

import junit.framework.TestCase;
import edu.cmpe273.univserver.beans.Course;
import edu.cmpe273.univserver.dao.CourseDAO;

public class CourseDAOTest extends TestCase {

	CourseDAO courseDAO = new CourseDAO();

	public void testgetStudentInvoice() {
		assertNotNull(courseDAO.getStudentInvoice("46"));
		assertNotNull(courseDAO.getStudentInvoice("1232"));
	}

	public void testadminAddCourse() {
		Course course = new Course();
		course.setCourseDesc("TestCourseDescription1");
		course.setCourseName("TestCourseName1");
		course.setCredits("3");
		course.setDay("Friday");
		course.setDepartment("CMPE");
		course.setLocation("Engineering building");
		course.setSection("02");
		course.setTime("1:00 PM to 2:00 PM");
		course.setCourseNumber("se280");

		assertEquals("Course Added Successfully",
				courseDAO.adminAddCourse(course));
	}

	public void testadminEditCourse() {
		Course course = new Course();
		course.setCourseNumber("se280");
		course.setCourseDesc("TestCourseDescription1");
		course.setCourseName("TestCourseName1");
		course.setCredits("3");
		course.setDay("Thursday");
		course.setDepartment("CMPE");
		course.setLocation("Engineering building");
		course.setSection("02");
		course.setTime("1:00 PM to 2:00 PM");

		assertEquals("Course Edited Successfully",
				courseDAO.adminEditCourse(course));

	}

	public void testgetCourseDetails() {
		// dept, courseno, sectno
		Course course = new Course();

		course.setCourseNumber("se273");
		course.setDepartment("SE");
		course.setSection("02");

		assertNotNull("Course Deleted Successfully",
				courseDAO.getCourseDetails(course));

	}

	public void testViewCourses() {
		assertNotNull("46");
	}

	public void testAddcourse() {

		assertEquals("Enrolled Successfully", courseDAO.Addcourse("50",
				"se280", "EST Overview", "02", "Monday", "1PM to 2 PM", "BBC"));

	}

	public void testadminDeleteCourse() {
		Course course = new Course();

		course.setCourseNumber("se280");
		course.setDepartment("CMPE");
		course.setSection("02");

		assertEquals("Course Does not Exist",
				courseDAO.adminDeleteCourse(course));
	}
	/*
	 * public void testaddCourseinBatch() {
	 * 
	 * Course[] course = new Course[1];
	 * course[0].setCourseDesc("TestCourseDescription1");
	 * course[0].setCourseName("TestCourseName1"); course[0].setCredits("3");
	 * course[0].setDay("Friday"); course[0].setDepartment("CMPE");
	 * course[0].setLocation("Engineering building");
	 * course[0].setSection("02"); course[0].setTime("1:00 PM to 2:00 PM");
	 * course[0].setCourseNumber("se274");
	 * 
	 * assertNotNull(courseDAO.addCourseinBatch(course));
	 * 
	 * }
	 */
}
