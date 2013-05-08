package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cmpe273.univserver.beans.StudentCourse;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class Student_drop {
	public  String dropCourse(String sjsuid,String name,String section) 
	{
		// TODO Auto-generated method stub
		StudentCourse course = null;
		DatabaseConnection db = null;
		Connection conn = null;
		try
		{	System.out.println(sjsuid);
			System.out.println(name);
			System.out.println(section);
			db = new DatabaseConnection();
			conn = db.getConnection();
			String sql = "DELETE FROM student_course WHERE SJSU_ID=? AND COURSE_NO=? and Course_Sec=?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sjsuid);
			pstmt.setString(2, name);
			pstmt.setString(3, section);
			
			int i=pstmt.executeUpdate();
			conn.commit();
			if(i==1)
			{		
				return "Dropped Successfully";
			}
			else
			{
				return "Drop Unsuccessfull";
			}

		

		} 
		catch (SQLException sqle) 
		{
			return "Drop Unsuccessfull";
		}

	
		finally
		{	
			db.closeConnection(conn);
			System.out.println("Connection closed");
		}

	

	}
		
}
