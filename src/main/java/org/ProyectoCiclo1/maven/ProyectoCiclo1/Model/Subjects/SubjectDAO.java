package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.Entry;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.User;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils.Connect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SubjectDAO extends Subject {
	private final static String getByID="SELECT * FROM subject WHERE ID=?";
	private final static String getByName="SELECT * FROM subject WHERE Name LIKE ? AND ID_User=?";
	private final static String getByOwner="SELECT * FROM subject WHERE ID_User=?";
	private final static String insertUpdate="INSERT INTO subject (ID,Name,ID_User) VALUES (?,?,?) ON DUPLICATE KEY UPDATE Name=?";
	private final static String delete="DELETE FROM subject WHERE ID=?";
	
	public SubjectDAO(Integer ID, String name, User owner) {
		super(ID,name,owner);
	}
	public SubjectDAO(Integer ID, String name) {
		super(ID,name);
	}
	public SubjectDAO(String name, User owner) {
		super(name,owner);
	}
	public SubjectDAO(Subject subject) {
		this.ID=subject.ID;
		this.name=subject.name;
		this.owner=subject.owner;
	}
	public SubjectDAO(Integer ID) {
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByID);
				query.setInt(1, ID);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					this.ID=rs.getInt("ID");
					this.name=rs.getString("Name");
					this.owner=new UserDAO(rs.getInt("ID_User"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
							new UserDAO(rs.getInt("ID_User"))));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	public static List<Subject> searchByName(String name, User user) {
		List<Subject> queryResult=new ArrayList<Subject>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByName);
				query.setString(1, "%"+name+"%");
				query.setInt(2, user.getID());
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new Subject(
							rs.getInt("ID"),
							rs.getString("Name"),
							new UserDAO(rs.getInt("ID_User"))));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	public static ObservableList<Subject> searchByOwner(User owner) {
		ObservableList<Subject> queryResultList=FXCollections.observableArrayList();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByOwner);
				query.setInt(1, owner.getID());
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResultList.add(new Subject(
							rs.getInt("ID"),
							rs.getString("Name")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResultList;
	}
	
	public static Boolean checkExists(String name, User user) {
		Boolean result=false;
		Connection con=Connect.getConnection();
		try {
			if(con!=null) {
				List<Subject> search=searchByName(name, user);
				if(search.isEmpty()==false) {
					if(search.get(0).name.equals(name)) {
						result=true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int save() {
		int result=0;
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(insertUpdate);
				try {
					query.setInt(1, this.ID);
				}catch (NullPointerException e){
					query.setNull(1, 0);
				}
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
				this.owner=new User(-666,"","");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
