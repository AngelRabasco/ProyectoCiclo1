package org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private static Connection con;
	private final static String server=XMLReader.getConectionInfo("server");
	private final static String database=XMLReader.getConectionInfo("database");
	private final static String username=XMLReader.getConectionInfo("user");
	private final static String password=XMLReader.getConectionInfo("password");
	
	public static void connect() {
		//Crea una conexión con la base de datos
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(server+"/"+database,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			con=null;
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		//Si la conexión no existe la crea
		if(con==null) {
			connect();
		}
		return con;
	}
}
