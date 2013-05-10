package edu.cmpe273.univserver.connection;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class DatabaseConnection {

	static String url = "jdbc:mysql://localhost:3306/universitydb";
	static String uname = "root";
	static String pwd = "root";

	static Queue<Connection> pool = new LinkedList<Connection>();
	private static  int MAXPOOLSIZE = 40;
	private static  int MINPOOLSIZE = 30;
	private static boolean onConnectionPool = true;
	private static boolean created = false;
	private static int poolsize=0;
	private static ArrayList<Connection> ca = new ArrayList<Connection>();
	public static void initialize(){
		if (onConnectionPool) {
			if (!created) {
				
				int i=0;
				for ( i = 0; i < MINPOOLSIZE; i++) {
					Connection con=createConnection();
					ca.add(con);
					pool.add(con);
					poolsize++;
				}
				System.out.println("Number of Connections created:"+i);
				created = true;
			}

		}
	}

	public Connection getConnection() {
		

			return getConnectionFromQueue();
		
		
	}

	static private Connection createConnection() {
		Connection conn = null;
		while (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						url + "?cachePrepStmts=true", uname, pwd);
				
			} catch (Exception e) {

				e.printStackTrace();

				if (conn == null) {
					System.out
							.println("Could not get connection...Trying again");
				}

			}
		}
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public Connection getConnectionFromQueue() {
		Connection conn = null;
		synchronized (pool) {
			if (!pool.isEmpty())
				{//System.out.println("using the "+pool.size()+" connection");
				conn = pool.poll();
				//System.out.println("pool size after using the object"+pool.size());
				}
			else {
					
				if (poolsize < MAXPOOLSIZE) {
												System.out.println("Creating "+(MAXPOOLSIZE-MINPOOLSIZE)+" more connections");
												for(int j=0;j<MAXPOOLSIZE-MINPOOLSIZE;j++)
												{	Connection con=createConnection();
													ca.add(con);
													pool.add(con);
													poolsize++;
												}
												
												conn = pool.poll();
												//System.out.println("Using the newley created connection"+pool.size());
					
											} else {
							
													System.out.println("Creating Connection out of the pool and increasing pool size.....");
													try {
														MAXPOOLSIZE++;
														
														Connection cnew=createConnection();	
														
														
														ca.add(cnew);
														pool.add(cnew);
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													conn = pool.poll();
							
											}
				}
		}
		return conn;
	}

	

	public void closeConnection(Connection conn) {
		
		{
			
			pool.add(conn);
		}
		

	}

	public static void releasePool() {
		
		for (Connection c : ca) {
			try { 
				c.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			

		}
		System.out.println("Number of Connections Properly Closed "+ca.size());
		System.out.println(pool.size());
		
		
			
		}
	}


