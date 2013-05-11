package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import edu.cmpe273.uniserver.util.Cache;
import edu.cmpe273.univserver.beans.Course;
import edu.cmpe273.univserver.beans.StudentCourse;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class CourseDAO {
	private static final int CACHESIZE = 75;
	
	static int courseHits = 0;
	static	int courseMiss = 0;
	Map<String, Course> courseset = Collections.synchronizedMap(new Cache<String, Course>(CACHESIZE));
	
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
		} finally {
			db.closeConnection(conn);
		}

		return invoiceReply;
	}

	public String adminAddCourse(Course course) {
		String course_name = course.getCourseName();
		String course_number = course.getCourseNumber();
		String credits = course.getCredits();
		String description = course.getCourseDesc();
		String dept = course.getDepartment();
		String section = course.getSection();

		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();

		String sql = "insert into courses(course_no,course_name,course_desc,section_no,credits,department) values(?,?,?,?,?,?)";

		try {
			// System.out.println("inside");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, course_number);
			ps.setString(2, course_name);
			ps.setString(3, description);
			ps.setString(4, section);
			ps.setString(5, credits);
			ps.setString(6, dept);

			if (ps.executeUpdate() == 1) {
				insertCourseCache(course);
				conn.commit();
				return "Course Added Successfully";
			} else {
				return "Course Adding Failure:Try Again";
			}

		} catch (Exception e) {

			// e.printStackTrace();
			return "Course Adding Failure:Duplicate Entry";
		} finally {
			db.closeConnection(conn);
		}
	}

	public String adminEditCourse(Course course) {
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();

		String course_name = course.getCourseName();
		String course_number = course.getCourseNumber();
		String credits = course.getCredits();
		String description = course.getCourseDesc();
		String dept = course.getDepartment();
		String section = course.getSection();

		try {

			String sql = "update courses set course_name=?,course_desc=?,credits=? where department=? and course_no=? and section_no=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, course_name);
			ps.setString(2, description);
			ps.setString(3, credits);
			ps.setString(4, dept);
			ps.setString(5, course_number);
			ps.setString(6, section);

			if (ps.executeUpdate() == 1) {
				System.out.println(sql);
				insertCourseCache(course);
				conn.commit();
				return "Course Edited Successfully";
			} else {
				return "Course Does Not  Exist!!!";
			}

		} catch (Exception e) {
			e.printStackTrace();

			return "Failure Try Again!!!";
		} finally {
			db.closeConnection(conn);
		}

	}

	public String adminDeleteCourse(Course c) {
		String Dept = c.getDepartment();
		String course_number = c.getCourseNumber();
		String section = c.getSection();
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();

		String sql = "delete from courses where department='" + Dept
				+ "' and course_no='" + course_number + "' and section_no='"
				+ section + "'";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			if (ps.executeUpdate() == 1) {
				deleteCourseFromCache(c);
				conn.commit();
				return "Course Deleted Successfully";
			} else {
				return "Course Does not Exist";
			}

		} catch (Exception e) {

			// e.printStackTrace();
			return "Failure:Try Again";
		}finally{
			db.closeConnection(conn);
		}
	}
		

	public Course getCourseDetails(Course c) {
		String Dept = c.getDepartment();
		String course_number = c.getCourseNumber();
		String section = c.getSection();
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		
		Course cc=getCourseFromCache(c.getCourseName());
		if(cc!=null)
		{
			return cc;
		}
		else
		{
			
		
		try {
			String sql = "Select * from Courses where department='" + Dept
					+ "' and course_no='" + course_number + "' and section_no='"
					+ section + "'";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c.setCourseNumber(rs.getString(1));
				c.setCourseName(rs.getString(2));
				c.setCourseDesc(rs.getString(3));
				c.setSection(rs.getString(4));
				c.setCredits(rs.getString(5));
				c.setDepartment(rs.getString(6));

			} else {
				return null;
			}
		} catch (Exception e) {

			e.printStackTrace();

		}finally{
			db.closeConnection(conn);
		return c;
		}
	}
	}	

	public static StudentCourse[] ViewCourses(String sjsuid) {
		// TODO Auto-generated method stub
		StudentCourse[] course = null;
		DatabaseConnection db = null;
		Connection conn = null;
		try {
			db = new DatabaseConnection();
			conn = db.getConnection();
			String sql = "SELECT COURSE_NO, COURSE_DAY,COURSE_TIME,COURSE_LOCATION,COURSE_SEC "
					+ "FROM student_course WHERE SJSU_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sjsuid);
			ResultSet rs = pstmt.executeQuery();

			int numberOfRows = 0, iCount = 0;
			while (rs.next()) {
				numberOfRows++;
			}
			course = new StudentCourse[numberOfRows];
			rs.beforeFirst();

			while (rs.next()) {
				StudentCourse studentCourse = new StudentCourse();
				System.out.println(rs.getString("COURSE_NO"));
				studentCourse.setCourseNumber(rs.getString("COURSE_NO"));
				studentCourse.setCourseSection(rs.getString("COURSE_SEC"));
				studentCourse
						.setCourseLocation(rs.getString("COURSE_LOCATION"));
				studentCourse.setCourseDay(rs.getString("COURSE_DAY"));
				studentCourse.setCourseTime(rs.getString("COURSE_TIME"));

				course[iCount] = studentCourse;
				iCount++;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(conn);
		}

		return course;

	}

	public String Addcourse(String sjsuid, String courseNumber,
			String courseName, String section, String day, String time,
			String location) {
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();

		String sql = "INSERT INTO student_course(SJSU_ID, COURSE_NO, COURSE_SEC, COURSE_DAY,"
				+ "COURSE_LOCATION, COURSE_TIME) values (?,?,?,?,?,?)";
		try {

			System.out.println("INSIDE ");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(sjsuid));
			ps.setString(2, courseNumber);
			ps.setInt(3, Integer.parseInt(section));
			ps.setString(4, day);
			ps.setString(5, time);
			ps.setString(6, location);

			if (ps.executeUpdate() == 1) {
				conn.commit();
				return "Enrolled Successfully";
			} else {
				return "Enrollment Not Successfull";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Course Adding Failure:Duplicate Entry";
		} finally {
			db.closeConnection(conn);
		}
	}

	public int addCourseinBatch(Course[] co) {
		int[] rowcount = new int[1000];
		PreparedStatement ps = null;
		DatabaseConnection db = new DatabaseConnection();

		Connection conn = db.getConnection();
		int size = co.length;
		int count = 0;
		int i = 0;
		// Connection conn = obj.getConnection();
		if (conn != null) {

			try {

				String sql = "insert into courses(course_no,course_name,course_desc,section_no,credits,department) values(?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				for (i = 0; i < co.length; i++) {
					ps.setString(1, co[i].courseNumber);
					ps.setString(2, co[i].courseName);
					ps.setString(3, co[i].courseDesc);
					ps.setString(4, co[i].section);
					ps.setString(5, co[i].credits);
					ps.setString(6, co[i].department);
					ps.addBatch();
					if (++count % 1000 == 0) {
						ps.executeBatch();
					}
				}

				conn.commit();
				db.closeConnection(conn);

			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

		return i;
	}
	
	
	public void insertCourseCache(Course course) {

		courseset.put(course.getCourseName(), course);
		
	}

	public void deleteCourseFromCache(Course course) {

		courseset.remove(course.getCourseName());

	}

	public Course getCourseFromCache(String coursename) {

		Course course = courseset.get(coursename);
		if (course != null)
			{courseHits++;
		System.out.println("Course found good use of Cache");
			}
		else
			{
			System.out.println("Course Not found in Cache");
			courseMiss++;
			}
		return course;

	}

}
