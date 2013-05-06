package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cmpe273.univserver.beans.Course;
import edu.cmpe273.univserver.beans.StudentCourse;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class CourseDAO {

	public StudentCourse[] getStudentInvoice(String sjsuid) {
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

	public String adminAddCourse(Course course)
	{	String course_name=course.getCourseName();
		String course_number=course.getCourseName();
		String credits=course.getCredits();
		String description=course.getCourseDesc();
		String dept=course.getDepartment();
		String section = course.getSection();
		
		DatabaseConnection db = new DatabaseConnection();
		Connection conn= db .getConnection();
		
		String sql= "insert into courses(course_no,course_name,course_desc,section_no,credits,department) values(?,?,?,?,?,?)";
		
		
		try {
			System.out.println("inside");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, course_number);
			ps.setString(2,course_name);
			ps.setString(3, description);
			ps.setString(4, section);
			ps.setString(5, credits);
			ps.setString(6, dept);
			
			if(ps.executeUpdate()==1)
			{
				return "Course Added Successfully";
			}
			else
			{
				return "Failure";
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "Duplicate Entry";
		}
		finally
		{
			
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
}
	
	public String editAddCourse(Course c)
	{
		DatabaseConnection db = new DatabaseConnection();
		Connection conn= db .getConnection();
		
		
		
		
		try {
			
			String sql= "update courses set (course_no,course_name,course_desc,section_no,credits,department) values(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
	
			
			if(ps.executeUpdate()==1)
			{
				return "Course Added Successfully";
			}
			else
			{
				return "Failure";
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "Duplicate Entry";
		}
		
	}
	public String deleteAddCourse(Course c)
	{
		String Dept=c.getDepartment();
		String course_number=c.getCourseNumber();
		String section= c.getSection();
		DatabaseConnection db = new DatabaseConnection();
		Connection conn= db .getConnection();
		
		String sql= "delete courses where department='"+Dept+"' and course_no='"+course_number+"' and section_no='"+section+"'";
		
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			if(ps.executeUpdate()==1)
			{
				return "Course Deleted Successfully";
			}
			else
			{
				return "Course Does not Exist";
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "Failure";
		}
		
		
	}
	public Course getCourse(Course c)
	{	
		return c;
	}
}
