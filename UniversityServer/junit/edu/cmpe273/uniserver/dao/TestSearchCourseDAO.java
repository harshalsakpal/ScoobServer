package edu.cmpe273.uniserver.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import edu.cmpe273.univserver.dao.SearchCourseDAO;

public class TestSearchCourseDAO {
	
	 
	
	@Test
	public void testSearchcourses() {
		try {
			assertNotNull(SearchCourseDAO.searchcourses("cmpe", "cmpe273"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
