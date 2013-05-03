package edu.cmpe273.uniserver.dao;

import edu.cmpe273.univserver.beans.StudentCourse;
import edu.cmpe273.uniserver.connection.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Coursedao 
{
public static StudenCourse[] getViewCourses(String sjsuid)
{
	StudentCourse[] studentCourse = null;
	DatabaseConnection db = null;
	Connection conn = null;
	try{
		
		
		db = new DatabaseConnection();
		conn = db.getConnection();
		
		String sql = "SELECT COURSE_NO, COURSE_DAY, COURSE_DAY, COURSE_TIME, COURSE_LOCATION, COURSE_SEC FROM student_course WHERE SJSU_ID = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, x)
		
	}
	catch(){
		
	}
}
}
