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
		return "Course Already Assigned to an Instructor";	
		}
		else
		{
		String sql="insert into instructor_course(department, section, course_no, SJSU_ID, DAY, TIME, Location, Available_Seats) values(?,?,?,?,?,?,?,?)";
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
			String sql= "Select SJSU_ID from Instructor_course where COURSE_NO=? and Department=? and SECTION=? ";
			
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
		
		public InstructorCourse getAssignedCourseDetails(InstructorCourse ic)
		{	
			  String dept=ic.getDepartment();
			  String coursenum=ic.getCourseNumber();
			  String section=ic.getSection();
			  
			DatabaseConnection db = new DatabaseConnection();
			Connection conn= db .getConnection();
			String sql= "Select SJSU_ID from Instructor_course where COURSE_NO=? and Department=? and SECTION=? ";
			
			try {
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, ic.getCourseNumber());
				ps.setString(2, ic.getDepartment());
				ps.setString(3, ic.getSection());
				ResultSet rs1=ps.executeQuery();
				if(rs1.next())
				{
					ic.setSjsuid(rs1.getString(1));
				}
				else
				{
					ic.setSjsuid(null);
				}
			}
			catch(Exception e)
			{
				
			}
			finally
			{
				db.closeConnection(conn);
			}
			return ic;
		}
		public String UpdateAssignCourse(InstructorCourse ic)
		{	System.out.println("Update Assign Called");
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
			
			System.out.println(availableSeats+""+day+""+sjsuid+""+location+""+time);
			String sql="update instructor_course set SJSU_ID=?,DAY=?,TIME=?,Location=?,Available_Seats=? where department='"+dept+"' and section='"+section+"' and course_no='"+coursenum+"'";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, sjsuid);
				ps.setString(2,day);
				ps.setString(3, time);
				ps.setString(4, location);
				ps.setInt(5, availableSeats);
			
				
				int i=ps.executeUpdate();
				conn.commit();
				if(i==1)
			{
					return "Editing Assigned Course:Successful";	
			}
				else
				{
					return "Editing Assigned Course:unsuccessful";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "Duplicate Entry: Course Already Assigned to This Professor";
			}
			
			finally{
				db.closeConnection(conn);
				
			}
			
			}
			
		
		
	}


