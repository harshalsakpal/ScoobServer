package edu.cmpe273.univserver.beans;

public class InstructorCourse {

	private String courseNumber;
	private String sjsuid;
	private String day;
	private String time;
	private String location;
	private String section;
	private String availableSeats;
	private String filledSeats;
	private String department;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getSjsuid() {
		return sjsuid;
	}

	public void setSjsuid(String sjsuid) {
		this.sjsuid = sjsuid;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(String availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getFilledSeats() {
		return filledSeats;
	}

	public void setFilledSeats(String filledSeats) {
		this.filledSeats = filledSeats;
	}

}
