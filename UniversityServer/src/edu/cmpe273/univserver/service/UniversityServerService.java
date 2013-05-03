package edu.cmpe273.univserver.service;

import javax.jws.WebService;

import edu.cmpe273.univserver.beans.Course;
import edu.cmpe273.univserver.beans.InstructorCourse;
import edu.cmpe273.univserver.beans.Person;
import edu.cmpe273.univserver.beans.StudentCourse;
import edu.cmpe273.univserver.dao.CourseDAO;

@WebService
public class UniversityServerService {

	public String testService() {
		return "Server Is Available";
	}

	public Person signIn(String username, String password) {
		Person person = new Person();

		return person;
	}

	public String registerUser(Person person) {

		return "";
	}

	public Course searchAllCourses(String category, String input) {
		Course course = new Course();
		return course;
	}

	public Person adminSignIn(String username, String password) {

		Person person = new Person();

		return person;
	}

	public String addCourse(StudentCourse[] studentCourse) {

		return "";

	}

	public String dropCourse(StudentCourse[] studentCourse) {

		return "";

	}

	public StudentCourse[] viewRegisteredCourse(String sjsuid) {
		StudentCourse[] studentCourse = null;
		return studentCourse;
	}

	public String addCourseToCart(StudentCourse[] studentCourse) {

		return "";

	}

	public String removeCourseFromCart(StudentCourse[] studentCourse) {

		return "";

	}

	public String adminAddCourse(Course course) {

		return "";
	}

	public String adminDeleteCourse(Course course) {

		return "";
	}

	public String adminEditCourse(Course course) {

		return "";
	}

	public Person listAllStudents() {

		Person person = new Person();

		return person;
	}

	public Person listAllProfessorss() {
		Person person = new Person();

		return person;
	}

	public String assignCourseToAProfessor() {

		return "";
	}

	public String editCourseAssignedToAProfessor() {

		return "";
	}

	public String editStudentInformation(Person person) {

		return "";
	}

	public String deleteStudentInformation(Person person) {

		return "";
	}

	public String deleteProfessorInformation(Person person) {

		return "";
	}

	public String editProfessorInformation(Person person) {

		return "";
	}

	public InstructorCourse[] viewAssignedCourses(String sjsuid) {
		InstructorCourse[] instructorCourses = null;

		return instructorCourses;
	}

	public StudentCourse[] getCourseInvoice(String sjsuid) {
		StudentCourse[] invoiceReply = CourseDAO.getStudentInvoice(sjsuid);

		return invoiceReply;
	}
}