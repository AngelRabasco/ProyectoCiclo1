package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.User;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils.Connect;

public class SubjectDAO extends Subject {
	private final static String getByID="SELECT * FROM subject WHERE ID=?";
	private final static String getByName="SELECT * FROM subject WHERE Name=?";
	private final static String getByOwner="SELECT * FROM subject WHERE User=?";
	private final static String insertUpdate="INSERT INTO subject (ID,Name,User) VALUES (?,?,?) ON DUPLICATE KEY UPDATE Name=?";
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
		Subject subject=searchByID(ID).get(0);
		this.ID=subject.ID;
		this.name=subject.name;
		this.owner=subject.owner;
	}
	public SubjectDAO() {
		super();
	}
	
	public static List<Subject> searchByID(Integer ID) {
		List<Subject> queryResult=new ArrayList<Subject>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByID);
				query.setInt(1, ID);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new Subject(
							rs.getInt("ID"),
							rs.getString("Name"),
							UserDAO.searchByID(rs.getInt("User")).get(0)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	public static List<Subject> searchByName(String name) {
		List<Subject> queryResult=new ArrayList<Subject>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByName);
				query.setString(1, "%"+name+"%");
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new Subject(rs.getInt("ID"),rs.getString("Name"),UserDAO.searchByID(rs.getInt("User")).get(0)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	public static List<Subject> searchByOwner(User owner) {
		List<Subject> queryResultList=new ArrayList<Subject>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByOwner);
				query.setInt(1, owner.getID());
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResultList.add(new Subject(rs.getInt("ID"),rs.getString("Name"),UserDAO.searchByID(rs.getInt("User")).get(0)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResultList;
	}
	
	public int save() {
		int result=0;
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(insertUpdate);
				query.setInt(1, this.ID);
				query.setString(2, this.name);
				query.setInt(3, this.owner.getID());
				query.setString(4, this.name);
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
				this.owner=new User(-666,"");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
