package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import edu.cmpe273.uniserver.util.Cache;
import edu.cmpe273.univserver.beans.Person;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class PersonDAO {
	private static final int CACHESIZE = 1;
	static	int Personhits = 0;
	static  int PersonMiss = 0;
	
	Map<String, Person> Personset = Collections.synchronizedMap(new Cache<String, Person>(CACHESIZE));
	public boolean AdminSignIn(String username, String password) {
		boolean flag = false;
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		try {
			String sql = "Select username from admin where username=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
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
		p=getPersonFromCache(username);
		
		if(p!=null)
		{
			return p;
		}
		else
		{
			try {
		
			String sql = "Select * from person where SJSUID=? and password=?";
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
				
			
			if (resultSet.next()) {
				System.out.println("SJSU ID RETURNED IS :: "
						+ resultSet.getString("SJSUID"));
				sjsuid = "Email id already Exists";
			}
			else

			 {
				sql = "INSERT INTO PERSON (`FIRST_NAME`,`LAST_NAME`,`ADDR_LINE_1`,`ADDR_LINE_2`,`CITY_NAME`,"
						+ "`STATE_NAME`,`ZIPCODE`,`EMAIL_ID`,`PASSWORD`,`DATEOFBIRTH`,`GENDER`,`ROLE`,`CONTACT_NUMBER`,`DEPARTMENT`) "
						+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
				conn.commit();
				insertPersonInCache(person);
				System.out.println("After Executing insert");

				sql = "SELECT SJSUID FROM PERSON WHERE EMAIL_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, person.getEmailid());
				resultSet = ps.executeQuery();

				
				
				
				System.out.println("After Executing Select");
				if (resultSet.next()) {
					sjsuid = resultSet.getString("SJSUID");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			return "Registration Unsuccessful";
		} finally {
			try {
				db.closeConnection(conn);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

		}
		return sjsuid;
	}

	public String deleteStudentInformation(String sjsuid) {
		String flag = "Record Was Not Deleted";
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		try {
			String sql1 = "DELETE FROM PERSON WHERE SJSUID=? AND ROLE ='STUDENT'";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setString(1, sjsuid);

			String sql2 = "DELETE FROM STUDENT_COURSE where SJSU_ID=?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, sjsuid);

			if (ps1.executeUpdate() == 1 || ps2.executeUpdate() == 1)
			
			{	deletePersonFromCache(sjsuid);
				flag = "Record Deleted Successfully";
			}
			else
				flag = "No Record Found";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.closeConnection(conn);

		}
		return flag;

	}

	public String deleteProfessorInformation(String sjsuid) {
		String flag = "Record Was Not Deleted";
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		try {
			String sql1 = "DELETE FROM PERSON WHERE SJSUID = ? AND ROLE = 'INSTRUCTOR'";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setString(1, sjsuid);

			String sql2 = "DELETE FROM INSTRUCTOR_COURSE WHERE SJSU_ID = ?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, sjsuid);

			String sql3 = "DELETE FROM INSTRUCTOR WHERE SJSU_ID = ?";
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			ps3.setString(1, sjsuid);

			if (ps1.executeUpdate() == 1 || ps2.executeUpdate() == 1
					|| ps3.executeUpdate() == 1)
				{
				deletePersonFromCache(sjsuid);
				
				flag = "Record Deleted Successfully";
				}
			else
				flag = "No Record Found";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.closeConnection(conn);

		}
		return flag;

	}

	public Person getStudentInformation(String sjsuid) {

		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		Person p = null;

		ResultSet rs;
		
			p=getPersonFromCache(sjsuid);
			if(p!=null)
			{
				return p;
			}
			else
			{
				try {
				String sql = "Select * from person where SJSUID=? And Role = 'STUDENT'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sjsuid);
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
				//p.setRole(rs.getString(13));
				// p.setContactNumber(rs.getString(14));
				p.setDepartment(rs.getString(15));
				// return p;
				return p;
				} 
			else {
				p = null;
				return p;
				}
				
		} catch (SQLException e) {

						e.printStackTrace();
						p = null;
						return p;
		} 
				finally {
								try {
									conn.commit();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								db.closeConnection(conn);
						}
		}
		
	}
	
	public Person getProfessorInformation(String sjsuid) {

		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		Person p = null;

		ResultSet rs;
		p=getPersonFromCache(sjsuid);
		if(p!=null)
		{
			return p;
		}
		else
		{
		try {
			String sql = "Select * from person where SJSUID=? And Role = 'INSTRUCTOR'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sjsuid);
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
				//p.setRole(rs.getString(13));
				// p.setContactNumber(rs.getString(14));
				p.setDepartment(rs.getString(15));
				return p;
			} else {
				p = null;
				return p;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p = null;
			return p;
		} finally {
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.closeConnection(conn);
		}
		}
		
	}

	public String editProfessorInformation(Person person){
		String flag = "Record Was Not Updated";
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		try {
			//TODO WRITE UPDATE STATEMENT....
			
			String sql1 = "UPDATE PERSON SET FIRST_NAME = ?, LAST_NAME= ?, ADDR_LINE_1= ?, ADDR_LINE_2= ?, CITY_NAME= ?, STATE_NAME= ?, ZIPCODE= ?, " +
					"EMAIL_ID= ?, PASSWORD= ?, DATEOFBIRTH= ?, DEPARTMENT= ?  " +
					"WHERE SJSUID= ? ";
			
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setString(1, person.getFirstName());
			ps1.setString(2, person.getLastName());
			ps1.setString(3, person.getAddrLine1());
			ps1.setString(4, person.getAddrLine2());
			ps1.setString(5, person.getCityName());
			ps1.setString(6, person.getStateName());
			ps1.setString(7, person.getZipCode());
			ps1.setString(8, person.getEmailid());
			ps1.setString(9, person.getPassword());
			ps1.setString(10, person.getDateOfBirth());
			ps1.setString(11, person.getDepartment());
			ps1.setString(12, person.getSjsuid());
			

			if (ps1.executeUpdate() == 1 )
				{flag = "Update Success";
				insertPersonInCache(person);
				}
			else
				flag = "No Record Updated";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.closeConnection(conn);

		}
		return flag;

	}
	


public void insertPersonInCache(Person Person) {

	Personset.put(Person.getSjsuid(), Person);
	System.out.println("Person inserted into Cache");

}

public Person getPersonFromCache(String sjsuid) {

	Person Person = Personset.get(sjsuid);
	if (Person != null)
		{
		Personhits++;
		System.out.println("Person found good use of Cache");
		}
		
	else
		{
		System.out.println("Person Not found in Cache");
		PersonMiss++;
		}
	return Person;

}

public void deletePersonFromCache(String sjsuid) {

	Personset.remove(sjsuid);

}

}
