package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils.Connect;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends User {
	private final static String getByID="SELECT * FROM user WHERE ID=?";
	private final static String getByName="SELECT * FROM user WHERE name=?";
	private final static String insertUpdate="INSERT INTO user (ID,name) VALUES (?,?) ON DUPLICATE KEY UPDATE mame=?";
	private final static String delete="DELETE FROM user WHERE ID=?";
	
	public UserDAO(Integer ID, String name) {
		super(ID,name);
	}
	public UserDAO(String name) {
		super(name);
	}
	public UserDAO(User u) {
		this.ID=u.ID;
		this.name=u.name;
	}
	public UserDAO() {
		super();
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
	public static List<User> searchByID(Integer ID){
		List<User> queryResult=new ArrayList<User>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByID);
				query.setInt(1, ID);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new User(rs.getInt("ID"),rs.getString("name")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	public static List<User> searchByName(String name) {
		List<User> queryResult=new ArrayList<User>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByName);
				query.setString(1, "%"+name+"%");
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new User(rs.getInt("ID"),rs.getString("name")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	
	public int save() {
		int result=0;
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(insertUpdate);
				query.setInt(1, this.ID);
				query.setString(2, this.name);
				query.setString(3, this.name);
				result=query.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int remove() {
		int result=0;
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(delete);
				q.setInt(1, this.ID);
				result=q.executeUpdate();
				this.ID=-999;
				this.name="";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
