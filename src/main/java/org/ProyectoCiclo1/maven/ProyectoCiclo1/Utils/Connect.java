package org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private static Connection con;
	//Should go on XML
	private final static String server="jdbc:mysql://127.0.0.1";
	private final static String database="proyectociclo1";
	private final static String username="root";
	private final static String password="";
	
	public static void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(server+"/"+database,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			con=null;
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection() {
		if(con==null) {
			connect();
		}
		return con;
	}
}
