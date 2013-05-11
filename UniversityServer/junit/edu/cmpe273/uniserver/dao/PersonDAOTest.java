package edu.cmpe273.uniserver.dao;

import edu.cmpe273.univserver.beans.Person;
import edu.cmpe273.univserver.dao.PersonDAO;
import junit.framework.TestCase;

public class PersonDAOTest extends TestCase {
	PersonDAO p = new PersonDAO();
	
	public void testAdminValidSignIn() {
			assertEquals(true, p.AdminSignIn("a", "a"));
	}
	
	public void testAdminInValidSignIn() {
	
		
		assertEquals(false, p.AdminSignIn("Aa", "a"));
	}

	public void testValidMemberSignIn() {
		
		
		assertNotNull(p.MemberSignIn("44", "a"));
	}
	public void testInValidMemberSignIn() {
		
		
		assertNull(p.MemberSignIn("123", "123"));
	}

	public void testListAllInstructors() {
		assertNotNull(p.listAllPersons("INSTRUCTOR"));
	}
	public void testListAllStudents() {
		assertNotNull(p.listAllPersons("STUDENT"));
	}

	public void testRegisterValidUser() {
		Person person = new Person();
		person.setAddrLine1("SanJose");
		person.setAddrLine2("Nellore");
		person.setCityName("Johanses");
		person.setContactNumber("9880314852");
		person.setDateOfBirth("07/17/1987");
		person.setDepartment("CMPE");
		person.setEmailid("leomanjucs@gmail.com");
		person.setFirstName("Manju");
		person.setGender("Male");
		person.setLastName("Sanju");
		person.setPassword("abc");
		person.setRole("STUDENT");
		person.setStateName("KA");
		person.setZipCode("95112");

		
		assertNotNull(p.registerUser(person));
	}
	
	public void testRegisterInValidUser() {
		Person person = new Person();
		person.setAddrLine1("SanJose");
		person.setAddrLine2("Nellore");
		person.setCityName("Johanses");
		person.setContactNumber("9880314852");
		person.setDateOfBirth("07/17/1987");
		person.setDepartment("CMPE");
		person.setEmailid("leomanjucs@gmail.com");
		person.setFirstName("Manju");
		person.setGender("Male");
		person.setLastName("Sanju");
		person.setPassword("abc");
		person.setRole("STUDENT");
		person.setStateName("KA");
		person.setZipCode("95112");

		
		assertEquals("Email id already Exists", p.registerUser(person));
	}


	public void testDeleteStudentInformation() {
		assertEquals("Record Deleted Successfully", p.deleteStudentInformation("48"));
	}
	public void testDeleteInvalidStudentInformation() {
		assertEquals("No Record Found", p.deleteStudentInformation("48"));
	}

	public void testDeleteProfessorInformation() {
		assertEquals("Record Deleted Successfully", p.deleteProfessorInformation("44"));
			}
	
	public void testDeleteProfessorInvalidInformation() {
		assertEquals("No Record Found", p.deleteProfessorInformation("44"));
			}
	

	public void testGetStudentInformation() {
		assertNotNull(p.getStudentInformation("41"));
	}
	
	public void testInvalidGetStudentInformation() {
		assertNull(p.getStudentInformation("43"));
	}
	

	public void testGetProfessorInformation() {
		assertNotNull(p.getProfessorInformation("43"));
	}

	public void testEditProfessorInformation() {
		assertNull(p.getProfessorInformation("41"));
	}



}
