package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cmpe273.univserver.beans.InstructorCourse;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class MyClass {
	public static InstructorCourse[] getInstClass(String sjsuid) {
		InstructorCourse[] MyClass = null;

		DatabaseConnection db = null;
		Connection conn = null;
		try {

			db = new DatabaseConnection();
			conn = db.getConnection();

			String sql =" SELECT COURSE_NO,DAY,TIME,LOCATION, SECTION FROM instructor_course WHERE SJSU_ID=?;";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, sjsuid);
			ResultSet rs = pst.executeQuery();
			
			int numberOfRows = 0, iCount = 0;

			while (rs.next()) {
				numberOfRows++;
			}
			MyClass = new InstructorCourse[numberOfRows];
			rs.beforeFirst();

			while (rs.next()) {
				InstructorCourse myCourse = new InstructorCourse();
				myCourse.setCourseNumber(rs.getString("COURSE_NO"));
				myCourse.setDay(rs.getString("DAY"));
				myCourse.setTime(rs.getString("TIME"));
			
				myCourse.setLocation(rs.getString("LOCATION"));
				myCourse.setSection(rs.getString("SECTION"));
				

				MyClass[iCount] = myCourse;
				iCount++;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.closeConnection(conn);
		}

		return MyClass;
	}
}
