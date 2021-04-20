package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils.Connect;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends User {
	private final static String getByID="SELECT * FROM User WHERE ID=";
	
	public UserDAO(Integer ID, String name) {
		super(ID,name);
	}
	public UserDAO(String name) {
		super(name);
	}
	public UserDAO() {
		super();
	}
	
	//DAO
	public UserDAO(User u) {
		this.ID=u.ID;
		this.name=u.name;
	}
	public UserDAO(Integer ID) {
		//getByID DB
		//Connection
		super();
		Connection con=Connect.getConnection();
		//Statement
		if(con!=null) {
			Statement st;
			try {
				st = con.createStatement();
				String q=getByID+"'"+ID+"'";
				ResultSet rs=st.executeQuery(q);
				while(rs.next()) {
					this.ID=rs.getInt("ID");
					this.name=rs.getString("name");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static List<User> searchByName(String name) {
		return null;
	}
	
	public int save() {
		//INSERT o UPDATE
		return 1;
	}
	public int remove() {
		//DELETE
		return 1;
	}
}
