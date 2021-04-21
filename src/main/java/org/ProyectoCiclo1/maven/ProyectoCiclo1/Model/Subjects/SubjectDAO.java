package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.User;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils.Connect;

public class SubjectDAO extends Subject {
	private final static String getByID="SELECT * FROM subject WHERE ID=?";
	private final static String getByName="SELECT * FROM subject WHERE Name=?";
	private final static String insertUpdate="INSERT INTO subject (ID,Name,User) VALUES (?,?,?) ON DUPLICATE KEY UPDATE Name=?,User=?";
	private final static String delete="DELETE FROM subject WHERE ID=?";
	
	public SubjectDAO(Integer ID, String name, User owner) {
		super(ID,name,owner);
	}
	public SubjectDAO(String name, User owner) {
		super(name, owner);
	}
	public SubjectDAO(Subject subject) {
		this.ID=subject.ID;
		this.name=subject.name;
		this.owner=subject.owner;
	}
	public SubjectDAO(Integer ID) {
		super();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByID);
				query.setInt(1, ID);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					this.ID=rs.getInt("ID");
					this.name=rs.getString("Name");
					this.owner=UserDAO.searchByID(rs.getInt("User")).get(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public SubjectDAO() {
		super();
	}
}
