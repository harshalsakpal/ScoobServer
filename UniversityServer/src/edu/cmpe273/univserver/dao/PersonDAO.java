package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cmpe273.univserver.beans.Person;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class PersonDAO {

	public boolean AdminSignIn(String username, String password) {
		boolean flag = false;
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		String admin = username;
		String pword = password;
		try {
			String sql = "Select username from admin where username=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, admin);
			ps.setString(2, pword);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			db.closeConnection(conn);

			try {
				conn.close();
			} catch (SQLException e) {

			}
		}
		return flag;
	}

	public Person MemberSignIn(String username, String password) {
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		String user = username;
		String pword = password;
		Person p = null;

		ResultSet rs;
		try {
			String sql="Select * from person where SJSUID=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pword);
			rs = ps.executeQuery();

			if (rs.next()) {
				p = new Person();
				p.setSjsuid(Integer.toString(rs.getInt(1)));
				p.setFirstName(rs.getString(2));
				p.setLastName(rs.getString(3));
				p.setAddrLine1(rs.getString(4));
				p.setAddrLine2(rs.getString(5));
				p.setCityName(rs.getString(6));
				p.setStateName(rs.getString(7));
				p.setZipCode(rs.getString(8));
				p.setEmailid(rs.getString(9));
				p.setPassword(rs.getString(10));
				p.setDateOfBirth(rs.getString(11));
				p.setGender(rs.getString(12));
				p.setRole(rs.getString(13));
				p.setContactNumber(rs.getString(14));
				p.setDepartment(rs.getString(15));
				return p;
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p = null;
			return p;
		}

	}

	public Person[] listAllPersons(String category) {
		Person[] personReply = null;
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		try {
			String sql = "";
			PreparedStatement ps = null;
			ResultSet rs = null;

			if (category.equalsIgnoreCase("INSTRUCTOR")
					|| category.equalsIgnoreCase("STUDENT")) {
				sql = "Select * from person where ROLE = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, category);
				rs = ps.executeQuery();
			} else {
				sql = "Select * from person";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
			}

			int numberOfRows = 0, iCount = 0;

			while (rs.next()) {
				numberOfRows++;
			}
			personReply = new Person[numberOfRows];
			rs.beforeFirst();

			while (rs.next()) {
				Person person = new Person();
				person.setSjsuid(Integer.toString((rs.getInt("SJSU_ID"))));
				person.setFirstName(rs.getString("FIRST_NAME"));
				person.setLastName(rs.getString("LAST_NAME"));
				person.setAddrLine1(rs.getString("ADDR_LINE_1"));
				person.setAddrLine2(rs.getString("ADDR_LINE_2"));
				person.setCityName(rs.getString("CITY_NAME"));
				person.setStateName(rs.getString("STATE_NAME"));
				person.setZipCode(rs.getString("ZIPCODE"));
				person.setEmailid(rs.getString("EMAIL_ID"));
				person.setDateOfBirth(rs.getString("DATEOFBIRTH"));
				person.setGender(rs.getString("GENDER"));
				person.setRole(rs.getString("ROLE"));
				person.setContactNumber(rs.getString("CONTACT_NUMBER"));
				person.setDepartment(rs.getString("DEPARTMENT"));

				personReply[iCount] = person;
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

		return personReply;

	}

	public String registerUser(Person person) {
		String sjsuid = "";
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		String sql = "";
		
		try {
			PreparedStatement ps = null;	
			ResultSet resultSet = null;
			sql = "SELECT SJSUID FROM PERSON WHERE EMAIL_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, person.getEmailid());
			resultSet = ps.executeQuery();
			
			while(resultSet.next()){
				System.out.println("SJSU ID RETURNED IS :: "+resultSet.getString("SJSUID"));
				sjsuid = "Email id already Exists";
			}
			resultSet.beforeFirst();
			
			if (!resultSet.next()) {			
				sql = "INSERT INTO PERSON (`FIRST_NAME`,`LAST_NAME`,`ADDR_LINE_1`,`ADDR_LINE_2`,`CITY_NAME`," +
						"`STATE_NAME`,`ZIPCODE`,`EMAIL_ID`,`PASSWORD`,`DATEOFBIRTH`,`GENDER`,`ROLE`,`CONTACT_NUMBER`,`DEPARTMENT`) " +
						"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, person.getFirstName());
				ps.setString(2, person.getLastName());
				ps.setString(3, person.getAddrLine1());
				ps.setString(4, person.getAddrLine2());
				ps.setString(5, person.getCityName());
				ps.setString(6, person.getStateName());
				ps.setString(7, person.getZipCode());
				ps.setString(8, person.getEmailid());
				ps.setString(9, person.getPassword());
				ps.setString(10, person.getDateOfBirth());
				ps.setString(11, person.getGender());
				ps.setString(12, person.getRole());
				ps.setString(13, person.getContactNumber());
				ps.setString(14, person.getDepartment());
				
				ps.executeUpdate();
				
				System.out.println("After Executing insert");
				
				sql = "SELECT SJSUID FROM PERSON WHERE EMAIL_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, person.getEmailid());
				resultSet = ps.executeQuery();
				
				System.out.println("After Executing Select");
				while(resultSet.next()){
					sjsuid = resultSet.getString("SJSUID");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Registration Unsuccessful";
		} finally {
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.closeConnection(conn);

			try {
				
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sjsuid;
	}

}
