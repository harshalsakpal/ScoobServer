package edu.cmpe273.univserver.service;

import javax.jws.WebService;

import edu.cmpe273.univserver.beans.Course;
import edu.cmpe273.univserver.beans.InstructorCourse;
import edu.cmpe273.univserver.beans.Person;
import edu.cmpe273.univserver.beans.StudentCourse;
import edu.cmpe273.univserver.dao.CourseDAO;
import edu.cmpe273.univserver.dao.PersonDAO;
import edu.cmpe273.univserver.validator.RegisterValidator;

@WebService
public class UniversityServerService {

	public String testService() {
		return "Server Is Available";
	}

	public Person signIn(String username, String password) 
	{	//System.out.println("Memeber Signin Called");
		PersonDAO pd = new PersonDAO();
		Person p = pd.MemberSignIn(username, password);
		return p;
	}

	public String registerUser(Person person) {
		RegisterValidator validator = new RegisterValidator();
		PersonDAO personDAO = new PersonDAO();
		String message = "";
		message = validator.validateRegisterInput(person);
		System.out.println("After Vaidating inputs at front end "+message);
		if("SUCCESS".equals(message)){
			message = personDAO.registerUser(person);
			System.out.println("SJSU ID created after inserting "+message);
		}
		return message;
	}

	public Course searchAllCourses(String category, String input) {
		Course course = new Course();
		
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
		return c.adminAddCourse(course);
		
	}

	public String adminDeleteCourse(Course course) {
		CourseDAO c = new CourseDAO();
		
		return  c.adminDeleteCourse(course); 
	}

	public String adminEditCourse(Course course) {
		CourseDAO c = new CourseDAO();
		return c.adminEditCourse(course);
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
	
	
	public String assignCourseToAProfessor(Course c, Person p) {

		return "";
	}

	public String editCourseAssignedToAProfessor() {

		return "";
	}

	public String editStudentInformation(Person person) {

		return "";
	}

	public String deleteStudentInformation(Person person) {
		PersonDAO personDAO = new PersonDAO();
		String deleteReply = personDAO.deleteStudentInformation(person.getSjsuid());
		System.out.println("Delete Reply >> "+deleteReply);
		return deleteReply;
	}

	public String deleteProfessorInformation(Person person) {
		PersonDAO personDAO = new PersonDAO();
		System.out.println("SJSU ID IN DELETE PROFESSOR>>> "+person.getSjsuid());
		String deleteReply = personDAO.deleteProfessorInformation(person.getSjsuid());
		System.out.println("Delete Reply >> "+deleteReply);
		return deleteReply;
	}
	

	public String editProfessorInformation(Person person) {

		return "";
	}
	
	public Person searchStudentInformation(String input){
		Person person = new Person();
		PersonDAO personDAO = new PersonDAO();
		person = personDAO.getStudentInformation(input);
		return person;
		
	}
	
	public Person searchInstructorInformation(String input){
		Person person = new Person();
		PersonDAO personDAO = new PersonDAO();
		person = personDAO.getProfessorInformation(input);
		return person;
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
	public Course getCourseDetails(Course course)
	{
		CourseDAO courseDAO = new CourseDAO();
		return courseDAO.getCourseDetails(course);
	}
}