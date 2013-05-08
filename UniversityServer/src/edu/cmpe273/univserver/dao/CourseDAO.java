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
		} finally {
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
				conn.commit();
				return "Course Added Successfully";
			} else {
				return "Course Adding Failure:Try Again";
			}

		} catch (Exception e) {

			// e.printStackTrace();
			return "Course Adding Failure:Duplicate Entry";
		} finally {

			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
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
				conn.commit();
				return "Course Edited Successfully";
			} else {
				return "Course Does Not  Exist!!!";
			}

		} catch (Exception e) {
			e.printStackTrace();

			return "Failure Try Again!!!";
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
				conn.commit();
				return "Course Deleted Successfully";
			} else {
				return "Course Does not Exist";
			}

		} catch (Exception e) {

			// e.printStackTrace();
			return "Failure:Try Again";
		}

	}

	public Course getCourseDetails(Course c) {
		String Dept = c.getDepartment();
		String course_number = c.getCourseNumber();
		String section = c.getSection();
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();

		String sql = "Select * from Courses where department='" + Dept
				+ "' and course_no='" + course_number + "' and section_no='"
				+ section + "'";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c.setCourseNumber(rs.getString(1));
				c.setCourseName(rs.getString(2));
				c.setCourseDesc(rs.getString(3));
				c.setSection(rs.getString(4));
				c.setCredits(rs.getString(5));
				c.setDepartment(rs.getString(6));
				return c;
			} else {
				return null;
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return c;
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
			System.out.println("Connection closed");
		}

		return course;

	}

	public String Addcourse(StudentCourse course) throws SQLException {
		String C_NO = course.getCourseNumber();
		String sjsu_id = course.getSjsuid();
		String section_no = course.getCourseSection();
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();

		String sql = "INSERT INTO student_course(SJSU_ID, COURSE_NO, COURSE_SEC, COURSE_DAY," +
				"COURSE_LOCATION, COURSE_TIME) values (?,?,?,?,?,?)";
		try {

			System.out.println("INSIDE ");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sjsu_id);
			ps.setString(2, C_NO);
			ps.setString(3, section_no);

			if (ps.executeUpdate() == 1) {
				conn.commit();
				return "Enrolled Successfully";
			} else {
				return "Enrollment Not Successfull";
			}

		} catch (Exception e) {
			return "Course Adding Failure:Duplicate Entry";
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
