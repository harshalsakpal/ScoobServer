package edu.cmpe273.uniserver.connection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class CreateConnectionPool extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	public void init(ServletConfig config) throws ServletException 
	{
		System.out.println("Creating  Connection Pool");
		DatabaseConnection.initialize();
	}


	public void destroy() {

		System.out.println("Server Stopped Releasing all the Used Connections");
		DatabaseConnection.releasePool();
	}

}
