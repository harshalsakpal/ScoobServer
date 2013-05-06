package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cmpe273.univserver.beans.StudentCourse;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class CourseDAO {

	public static StudentCourse[] getStudentInvoice(String sjsuid) {
		StudentCourse[] invoiceReply = null;

		DatabaseConnection db = null;
		Connection conn = null;
		try {

			db = new DatabaseConnection();
			conn = db.getConnection();

			String sql = "SELECT SJSU_ID, COURSE_NO, COURSE_DAY, COURSE_TIME, "
					+ "COURSE_LOCATION, COURSE_SEC FROM student_course WHERE SJSU_ID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, sjsuid);
			ResultSet rs = pst.executeQuery();

			int numberOfRows = 0, iCount = 0;

			while (rs.next()) {
				numberOfRows++;
			}
			invoiceReply = new StudentCourse[numberOfRows];
			rs.beforeFirst();

			while (rs.next()) {
				StudentCourse studentCourse = new StudentCourse();
				studentCourse.setSjsuid(rs.getString("SJSU_ID"));
				studentCourse.setCourseNumber(rs.getString("COURSE_NO"));
				studentCourse.setCourseSection(rs.getString("COURSE_SEC"));
				studentCourse
						.setCourseLocation(rs.getString("COURSE_LOCATION"));
				studentCourse.setCourseDay(rs.getString("COURSE_DAY"));
				studentCourse.setCourseTime(rs.getString("COURSE_TIME"));

				invoiceReply[iCount] = studentCourse;
				iCount++;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.closeConnection(conn);
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return invoiceReply;
	}

	public String adminAddCourse()
	{
		
		return "";
	}
	public String editAddCourse()
	{
		return "";
		
	}
	public String deleteAddCourse()
	{
		return "";
		
	}
}
