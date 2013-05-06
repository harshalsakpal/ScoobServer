package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.cmpe273.univserver.beans.Person;
import edu.cmpe273.univserver.connection.DatabaseConnection;

public class PersonDAO {
	
	public boolean AdminSignIn(String username,String password)
	{	boolean flag=false;
		DatabaseConnection db = new DatabaseConnection();
		Connection conn= db.getConnection();
		String admin=username;
		String pword=password;
		try {
			String sql="Select username from admin where username=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, admin);
			ps.setString(2, pword);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			db.closeConnection(conn);
			
			try {
				conn.close();
			} catch (SQLException e) {
			
			
			}
		}
		return flag;
	}
	
	public Person MemberSignIn(String username,String password)
	{	
		DatabaseConnection db = new DatabaseConnection();
		Connection conn= db.getConnection();
		String user=username;
		String pword=password;
		Person p = null;
	
		ResultSet rs;
		try {
			String sql="Select * from person where SJSUID=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pword);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				p=new Person();
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
				}
			else
			{
				return null;
			}
		
				
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p=null;
			return p;
		}
		

		 
	}
	
	

}
