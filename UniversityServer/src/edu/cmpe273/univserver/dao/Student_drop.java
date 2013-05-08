package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cmpe273.univserver.beans.StudentCourse;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class Student_drop {
	public static String dropCourse(String sjsuid,String name) 
	{
		// TODO Auto-generated method stub
		StudentCourse course = null;
		DatabaseConnection db = null;
		Connection conn = null;
		try
		{
			db = new DatabaseConnection();
			conn = db.getConnection();
			String sql = "DELETE FROM student_course WHERE SJSU_ID=? AND COURSE_NO=?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sjsuid);
			pstmt.setString(2, name);
			ResultSet rs = pstmt.executeQuery();
						
			rs.beforeFirst();
			

		

		} catch (SQLException sqle) 
		{
			sqle.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{	
			db.closeConnection(conn);
			System.out.println("Connection closed");
		}

		String s= "class dropped successfully";
		return s;

	}
		
}
