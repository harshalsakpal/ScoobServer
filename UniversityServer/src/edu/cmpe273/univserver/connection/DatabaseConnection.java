
package edu.cmpe273.univserver.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;


public class DatabaseConnection {

	static String url = "jdbc:mysql://localhost:3306/universitydb";
	static String uname = "root";
	static String pwd = "admin";

	static Queue<Connection> pool = new LinkedList<Connection>();
	private static final int MAXPOOLSIZE = 40;
	private static final int MINPOOLSIZE = 30;
	private static boolean onConnectionPool = true;
	private static boolean created = false;
	private static int poolsize=0;
	static Connection[] ca = new Connection[MAXPOOLSIZE];
	static void initialize(){
		if (onConnectionPool) {
			if (!created) {
				
				int i=0;
				for ( i = 0; i < MINPOOLSIZE; i++) {
					Connection con=createConnection();
					ca[i]=con;
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
				{System.out.println("using the "+pool.size()+" connection");
				conn = pool.poll();
				System.out.println("pool size after using the object"+pool.size());
				}
			else {
					
				if (poolsize < MAXPOOLSIZE) {
					System.out.println("Creating "+(MAXPOOLSIZE-MINPOOLSIZE)+" more connections");
					for(int j=0;j<MAXPOOLSIZE-MINPOOLSIZE;j++)
					{	Connection con=createConnection();
						ca[j+MINPOOLSIZE]=con;
						pool.add(con);
						poolsize++;
					}
					
					conn = pool.poll();
					System.out.println("Using the newley created connection"+pool.size());
					
				} else {
					while (conn == null) {
						System.out.println("waiting for connection");
						try {
							Thread.sleep(500);

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						conn = pool.poll();
					}
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
		
		
		
		try {int i=0;
				int j=0;
				for ( i = 0; i < ca.length; i++) {
					if ((ca[i]==null)){ 
						
					}
					else
					{	//System.out.println(ca[i].isClosed());
						ca[i].close();
						j++;
					}
					
					
				}
				System.out.println("Number of Connections Properly Closed "+j);
				System.out.println(pool.size());
				while(pool.size()>0)
				{
					System.out.println(pool.poll().isClosed());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
	}


