package edu.cmpe273.univserver.service;

import java.sql.SQLException;

import javax.jws.WebService;

import edu.cmpe273.univserver.beans.Course;
import edu.cmpe273.univserver.beans.InstructorCourse;
import edu.cmpe273.univserver.beans.Person;
import edu.cmpe273.univserver.beans.StudentCourse;
import edu.cmpe273.univserver.dao.CourseDAO;
import edu.cmpe273.univserver.dao.InstructorCourseDAO;
import edu.cmpe273.univserver.dao.MyClass;
import edu.cmpe273.univserver.dao.PersonDAO;
import edu.cmpe273.univserver.dao.SearchCourseDAO;
import edu.cmpe273.univserver.dao.Student_drop;
import edu.cmpe273.univserver.validator.RegisterValidator;

@WebService
public class UniversityServerService {

	public String testService() {
		return "Server Is Available";
	}

	public Person signIn(String username, String password) { // System.out.println("Memeber Signin Called");
		PersonDAO pd = new PersonDAO();
		Person p = pd.MemberSignIn(username, password);
		return p;
	}

	public String registerUser(Person person) {
		RegisterValidator validator = new RegisterValidator();
		PersonDAO personDAO = new PersonDAO();
		String message = "";
		message = validator.validateRegisterInput(person);
		System.out.println("After Vaidating inputs at front end " + message);
		if ("SUCCESS".equals(message)) {
			message = personDAO.registerUser(person);
			System.out.println("SJSU ID created after inserting " + message);
		}
		return message;
	}

	public Course[] searchAllCourses(String category, String input)
			throws SQLException {
		Course[] course = SearchCourseDAO.searchcourses(category, input);
		return course;
	}

	public boolean adminSignIn(String username, String password) {

		PersonDAO pd = new PersonDAO();
		System.out.println("adminSigninCalled");
		return pd.AdminSignIn(username, password);
	}

	public String addCourse(String sjsuid, String courseNumber, String courseName, String section, String day, String time, String location) {
		CourseDAO courseDAO = new CourseDAO();
		String addCourseReply = courseDAO.Addcourse(sjsuid, courseNumber,courseName,section,day,time,location);
		System.out.println("addCourseReply in service>>> "+addCourseReply);
		return addCourseReply;
	}

	public String dropCourse(String sjsu_id, String name,String section) {
		Student_drop sd= new Student_drop();
		String del = sd.dropCourse(sjsu_id, name,section);
		System.out.println("dropCourse in service>>> "+del);
		return del;

	}

	public StudentCourse[] viewRegisteredCourse(String sjsuid) {
		StudentCourse[] studentCourse = CourseDAO.ViewCourses(sjsuid);
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

		return c.adminDeleteCourse(course);
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

	public String assignCourseToAProfessor(InstructorCourse ic) {
		InstructorCourseDAO icd = new InstructorCourseDAO();

		return icd.AssignCourse(ic);
	}

	public String editCourseAssignedToAProfessor(InstructorCourse ic) {
		InstructorCourseDAO icd = new InstructorCourseDAO();
		
		return icd.UpdateAssignCourse(ic);
		}

	public String editStudentInformation(Person person) {

		return "";
	}

	public String deleteStudentInformation(Person person) {
		PersonDAO personDAO = new PersonDAO();
		String deleteReply = personDAO.deleteStudentInformation(person
				.getSjsuid());
		System.out.println("Delete Reply >> " + deleteReply);
		return deleteReply;
	}

	public String deleteProfessorInformation(Person person) {
		PersonDAO personDAO = new PersonDAO();
		System.out.println("SJSU ID IN DELETE PROFESSOR>>> "
				+ person.getSjsuid());
		String deleteReply = personDAO.deleteProfessorInformation(person
				.getSjsuid());
		System.out.println("Delete Reply >> " + deleteReply);
		return deleteReply;
	}

	public String editProfessorInformation(Person person) {
		PersonDAO personDAO = new PersonDAO();
		String editReply = personDAO.editProfessorInformation(person);
		System.out.println("Update Reply >> " + editReply);
		return editReply;
	}

	public Person searchStudentInformation(String input) {
		Person person = new Person();
		PersonDAO personDAO = new PersonDAO();
		person = personDAO.getStudentInformation(input);
		return person;
	}

	public Person searchInstructorInformation(String input) {
		Person person = new Person();
		PersonDAO personDAO = new PersonDAO();
		person = personDAO.getProfessorInformation(input);
		return person;
	}

	public InstructorCourse[] viewAssignedCourses(String sjsuid) {
		InstructorCourse[] instructorCourses = MyClass.getInstClass(sjsuid);

		return instructorCourses;
	}

	public StudentCourse[] getCourseInvoice(String sjsuid) {
		CourseDAO courseDAO = new CourseDAO();
		StudentCourse[] invoiceReply = courseDAO.getStudentInvoice(sjsuid);

		return invoiceReply;
	}

	public Course getCourseDetails(Course course) {
		CourseDAO courseDAO = new CourseDAO();
		return courseDAO.getCourseDetails(course);
	}

	public int addCourseInBatch(Course[] co) {
		CourseDAO c = new CourseDAO();
		return c.addCourseinBatch(co);
	}
	
	public InstructorCourse getAssignedCourse(InstructorCourse ic)
	{
		InstructorCourseDAO icd = new InstructorCourseDAO();
		InstructorCourse newic=icd.getAssignedCourseDetails(ic);
		return newic;
	}
	
}