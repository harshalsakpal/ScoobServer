package edu.cmpe273.univserver.beans;

public class StudentCourse {

	private String sjsuid;
	private String courseNumber;
	private String courseSection;
	private String courseDay;
	private String courseTime;
	private String courseLocation;
	private String courseProfessor;

	public String getSjsuid() {
		return sjsuid;
	}

	public void setSjsuid(String sjsuid) {
		this.sjsuid = sjsuid;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseSection() {
		return courseSection;
	}

	public void setCourseSection(String courseSection) {
		this.courseSection = courseSection;
	}

	public String getCourseDay() {
		return courseDay;
	}

	public void setCourseDay(String courseDay) {
		this.courseDay = courseDay;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public String getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}

	public String getCourseProfessor() {
		return courseProfessor;
	}

	public void setCourseProfessor(String courseProfessor) {
		this.courseProfessor = courseProfessor;
	}

}