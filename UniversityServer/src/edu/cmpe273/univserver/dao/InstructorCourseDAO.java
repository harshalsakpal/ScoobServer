package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cmpe273.univserver.beans.InstructorCourse;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class InstructorCourseDAO {


	
	public String AssignCourse(InstructorCourse ic)
	{	
		  String dept=ic.getDepartment();
		  String coursenum=ic.getCourseNumber();
		  String section=ic.getSection();
		  int availableSeats=Integer.parseInt(ic.getAvailableSeats());
		  String day=ic.getDay();
		  String sjsuid=ic.getSjsuid();
		  String location=ic.getLocation();
		  String time=ic.getTime();
		  
		DatabaseConnection db = new DatabaseConnection();
		Connection conn= db .getConnection();
		if(checkCourse(ic,conn))
		{
		return "Course Already Assigned to a Different Instructor";	
		}
		else
		{
		String sql="insert into instructor_course(department,section_no,course_no,SJSU_ID,DAY,TIME,Location,Available_Seats) values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dept);
			ps.setString(2,section);
			ps.setString(3, coursenum);
			ps.setString(4, sjsuid);
			ps.setString(5, day);
			ps.setString(6, time);
			ps.setString(7, location);
			ps.setInt(8, availableSeats);
			ps.executeUpdate();
			conn.commit();
		return "Course Assigned Successfully";	
		} catch (Exception e) {
			return "Duplicate Entry: Course Already Assigned to This Professor";
		}
		
		finally{
			db.closeConnection(conn);
			
		}
		
		}
		
	}
		private boolean checkCourse(InstructorCourse ic,Connection con)
		{	
			String sql= "Select SJSU_ID from Instructor_course where COURSE_NO=? and Department=? and SECTION_NO=? ";
			
			try {
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, ic.getCourseNumber());
				ps.setString(2, ic.getDepartment());
				ps.setString(3, ic.getSection());
				ResultSet rs1=ps.executeQuery();
				if(rs1.next())
				{
					return true;
				}
				else
				{
					return false;
				}
			} 
			catch (Exception e) {
				return false;
			}
	
			
			
		}
	}


