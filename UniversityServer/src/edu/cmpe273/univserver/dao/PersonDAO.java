package edu.cmpe273.univserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
