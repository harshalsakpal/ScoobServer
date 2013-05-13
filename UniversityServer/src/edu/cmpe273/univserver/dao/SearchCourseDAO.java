package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cmpe273.univserver.beans.Course;
import edu.cmpe273.univserver.beans.Person;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class SearchCourseDAO {
	static public Course[] searchcourses(String department, String courseNumber)
			throws SQLException {
		Course[] course = null;
		DatabaseConnection db = null;
		Connection conn = null;
		System.out.println("Department and courseNumber in DAO >> "
				+ department + "  " + courseNumber);

		try {
			db = new DatabaseConnection();
			conn = db.getConnection();
			String queryTemp = "SELECT C.COURSE_NO, C.COURSE_NAME, C.SECTION_NO, C.CREDITS, IC.DAY,"
					+ "IC.LOCATION, IC.TIME, C.DEPARTMENT FROM INSTRUCTOR_COURSE IC, COURSES C "
					+ "WHERE IC.COURSE_NO = C.COURSE_NO AND C.SECTION_NO = IC.SECTION AND "
					+ "C.COURSE_NO= ? AND C.DEPARTMENT = ?";

			PreparedStatement pstmt = conn.prepareStatement(queryTemp);

			pstmt.setString(1, courseNumber);
			pstmt.setString(2, department);

			ResultSet rs = pstmt.executeQuery();
			int numberOfRows = 0, iCount = 0;
			while (rs.next()) {
				numberOfRows++;
			}
			System.out.println("Number of rows>> " + numberOfRows);
			course = new Course[numberOfRows];
			rs.beforeFirst();

			while (rs.next()) {
				Course c = new Course();
				System.out.println(rs.getString("COURSE_NO"));
				c.setCourseNumber(rs.getString("COURSE_NO"));
				c.setCourseName(rs.getString("COURSE_NAME"));
				c.setSection(rs.getString("SECTION_NO"));
				c.setCredits(rs.getString("CREDITS"));
				c.setDepartment(rs.getString("DEPARTMENT"));
				c.setDay(rs.getString("DAY"));
				c.setTime(rs.getString("TIME"));
				c.setLocation(rs.getString("LOCATION"));

				course[iCount] = c;
				iCount++;
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		} finally {
			db.closeConnection(conn);
		}
		return course;

	}


	 public  Person[] listStudentsUnderCourse( Course c)
			 {
		
				DatabaseConnection db = null;
				Connection conn = null;
				Person[] p = null;

		try {
			db = new DatabaseConnection();
			conn = db.getConnection();

			String queryTemp = "Select p.sjsuid,p.first_name,p.last_name,p.email_id from Person p,instructor_course ic where p.sjsuid=ic.sjsu_id and ic.course_no='"+c.getCourseNumber()+"' and ic.section='"+c.getSection()+"' and ic.department='"+c.getDepartment()+"'";

			PreparedStatement pstmt = conn.prepareStatement(queryTemp);

			

			ResultSet rs = pstmt.executeQuery();
			int numberOfRows = 0, iCount = 0;
			
			while (rs.next()) {
				numberOfRows++;
			}
			
			p = new Person[numberOfRows];
			rs.beforeFirst();

			while (rs.next()) {
				Person pp = new Person();
				pp.setSjsuid(rs.getString(1));	
				pp.setFirstName(rs.getString(2));
				pp.setLastName(rs.getString(3));
				pp.setEmailid(rs.getString(4));
				p[iCount] = pp;
			}
			
		} catch (Exception sqle) {
			sqle.printStackTrace();
		} finally {
			db.closeConnection(conn);
		}
		return p;
	}
	 


	public Course[] getAllCourses() {

		Course[] course = null;
		DatabaseConnection db = null;
		Connection conn = null;

		try {
			db = new DatabaseConnection();
			conn = db.getConnection();
			String queryTemp = "SELECT * FROM COURSES ";

			PreparedStatement pstmt = conn.prepareStatement(queryTemp);

			ResultSet rs = pstmt.executeQuery();
			int numberOfRows = 0, iCount = 0;
			while (rs.next()) {
				numberOfRows++;
			}
			System.out.println("Number of rows>> " + numberOfRows);
			course = new Course[numberOfRows];
			rs.beforeFirst();

			while (rs.next()) {
				Course c = new Course();
				System.out.println(rs.getString("COURSE_NO"));
				c.setCourseNumber(rs.getString("COURSE_NO"));
				c.setCourseName(rs.getString("COURSE_NAME"));
				c.setSection(rs.getString("SECTION_NO"));
				c.setCredits(rs.getString("CREDITS"));
				c.setDepartment(rs.getString("DEPARTMENT"));
				c.setCourseDesc(rs.getString("COURSE_DESC"));

				course[iCount] = c;
				iCount++;
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		} finally {
			db.closeConnection(conn);
		}
		return course;

	}

}
