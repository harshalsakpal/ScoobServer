package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cmpe273.univserver.beans.Course;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class SearchCourseDAO {
	public static Course[] searchcourses(String department, String courseNumber)
			throws SQLException {
		Course[] course = null;
		DatabaseConnection db = null;
		Connection conn = null;
		System.out.println("Department and courseNumber in DAO >> "+department+"  "+courseNumber);

		try {
			db = new DatabaseConnection();
			conn = db.getConnection();
			String queryTemp = "SELECT C.COURSE_NO, C.COURSE_NAME, C.SECTION_NO, C.CREDITS, IC.DAY, "
					+ "IC.LOCATION, IC.TIME, C.DEPARTMENT FROM INSTRUCTOR_COURSE IC, COURSES C "
					+ "WHERE IC.COURSE_NO = C.COURSE_NO AND C.SECTION_NO = IC.SECTION AND "
					+ "C.COURSE_NO= ? AND C.DEPARTMENT = ? ";

			PreparedStatement pstmt = conn.prepareStatement(queryTemp);

			pstmt.setString(1, courseNumber);
			pstmt.setString(2, department);

			ResultSet rs = pstmt.executeQuery();
			int numberOfRows = 0, iCount = 0;
			while (rs.next()) {
				numberOfRows++;
			}System.out.println("Number of rows>> "+numberOfRows);
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

}
