package edu.cmpe273.univserver.service;

import javax.jws.WebService;

import edu.cmpe273.univserver.beans.Course;
import edu.cmpe273.univserver.beans.InstructorCourse;
import edu.cmpe273.univserver.beans.Person;
import edu.cmpe273.univserver.beans.StudentCourse;
import edu.cmpe273.univserver.dao.CourseDAO;
import edu.cmpe273.univserver.dao.PersonDAO;

@WebService
public class UniversityServerService {

	public String testService() {
		return "Server Is Available";
	}

	public Person signIn(String username, String password) {
		Person person = null;
//TODO DONE
		return person;
	}

	public String registerUser(Person person) {

		return "";
	}

	public Course searchAllCourses(String category, String input) {
		Course course = new Course();
		//TODO DONE
		return course;
	}

	public boolean adminSignIn(String username, String password) {

		PersonDAO pd = new PersonDAO();
		System.out.println("adminSigninCalled");
		return pd.AdminSignIn(username, password);
	}

	public String addCourse(StudentCourse[] studentCourse) {
		// DatabaseConnection db = null;
		
		return "";

	}

	public String dropCourse(StudentCourse[] studentCourse) {

		return "";

	}

	public StudentCourse[] viewRegisteredCourse(String sjsuid) {
		StudentCourse[] studentCourse = null;
		//TODO DONE
		return studentCourse;
	}

	public String addCourseToCart(StudentCourse[] studentCourse) {

		return "";

	}

	public String removeCourseFromCart(StudentCourse[] studentCourse) {

		return "";

	}

	public String adminAddCourse(Course course) {
		CourseDAO c = new CourseDAO();
		//TODO DONE
		return "";
	}

	public String adminDeleteCourse(int course_number, String dept) {
		CourseDAO c = new CourseDAO();
		//TODO DONE
		return "";
	}

	public String adminEditCourse(int course_number, String dept) {
		CourseDAO c = new CourseDAO();
		return "";
	}

	public Person[] listAllStudents() {

		Person[] person = null;
		PersonDAO personDAO = new PersonDAO();
		person = personDAO.listAllPersons("STUDENT");

		return person;
	}

	public Person[] listAllProfessors() {
		Person[] person = null;
		PersonDAO personDAO = new PersonDAO();
		person = personDAO.listAllPersons("INSTRUCTOR");

		return person;
	}

	public Person[] listAllPersons() {
		Person[] person = null;
		PersonDAO personDAO = new PersonDAO();
		person = personDAO.listAllPersons("ALL");

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
		CourseDAO courseDAO = new CourseDAO();
		StudentCourse[] invoiceReply = courseDAO.getStudentInvoice(sjsuid);

		return invoiceReply;
	}
}