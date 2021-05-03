package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.SubjectDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils.Connect;

public class ReminderDAO extends Reminder {
	private final static String getByID="SELECT * FROM entry WHERE ID=?";
	private final static String getByName="SELECT * FROM entry WHERE Name=?";
	private final static String getBySubject="SELECT * FROM entry WHERE Subject=?";
	private final static String insertUpdate="INSERT INTO entry (ID,Name,Description,Subject,CreationDate,LastEdited,ReminderTime,Status) VALUES (?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE Name=?,Description=?,Subject=?,LastEdited=?,ReminderTime=?,Status=?";
	private final static String delete="DELETE FROM entry WHERE ID=?";
	
	public ReminderDAO(Integer ID, String name, String description, Subject subject,LocalDateTime creation, LocalDateTime edited, LocalDateTime remind, Boolean status) {
		super(ID, name, description, subject, creation, edited, remind, status);
	}
	public ReminderDAO(Reminder reminder) {
		this.ID=reminder.ID;
		this.name=reminder.name;
		this.description=reminder.description;
		this.subject=reminder.subject;
		this.creationDate=reminder.creationDate;
		this.lastEdited=reminder.lastEdited;
		this.remindDate=reminder.remindDate;
		this.status=reminder.status;
	}
	public ReminderDAO(Integer ID) {
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByID);
				query.setInt(1, ID);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					this.ID=rs.getInt("ID");
					this.name=rs.getString("Name");
					this.description=rs.getString("Description");
					this.subject=new SubjectDAO(rs.getInt("ID"));
					this.creationDate=rs.getTimestamp("CreationDate").toLocalDateTime();
					this.lastEdited=rs.getTimestamp("LastEdited").toLocalDateTime();
					this.remindDate=rs.getTimestamp("ReminderTime").toLocalDateTime();
					this.status=rs.getBoolean("Status");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ReminderDAO() {
		super();
	}
	
	public static List<Reminder> searchByID(Integer ID){
		List<Reminder> queryResult=new ArrayList<Reminder>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByID);
				query.setInt(1, ID);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new Reminder(
							rs.getInt("ID"),
							rs.getString("Name"),
							rs.getString("Description"),
							new SubjectDAO(rs.getInt("ID")),
							rs.getTimestamp("CreationDate").toLocalDateTime(),
							rs.getTimestamp("LastEdited").toLocalDateTime(),
							rs.getTimestamp("ReminderTime").toLocalDateTime(),
							rs.getBoolean("Status")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	public static List<Reminder> searchByName(String name) {
		List<Reminder> queryResult=new ArrayList<Reminder>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByName);
				query.setString(1, name);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new Reminder(
							rs.getInt("ID"),
							rs.getString("Name"),
							rs.getString("Description"),
							new SubjectDAO(rs.getInt("ID")),
							rs.getTimestamp("CreationDate").toLocalDateTime(),
							rs.getTimestamp("LastEdited").toLocalDateTime(),
							rs.getTimestamp("ReminderTime").toLocalDateTime(),
							rs.getBoolean("Status")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	public static List<Reminder> searchBySubject(Subject subject) {
		List<Reminder> queryResult=new ArrayList<Reminder>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getBySubject);
				query.setInt(1, subject.getID());
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new Reminder(
							rs.getInt("ID"),
							rs.getString("Name"),
							rs.getString("Description"),
							new SubjectDAO(rs.getInt("ID")),
							rs.getTimestamp("CreationDate").toLocalDateTime(),
							rs.getTimestamp("LastEdited").toLocalDateTime(),
							rs.getTimestamp("ReminderTime").toLocalDateTime(),
							rs.getBoolean("Status")));
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
				query.setString(3, this.description);
				query.setInt(4, this.subject.getID());
				query.setTimestamp(5, Timestamp.valueOf(this.creationDate));
				query.setTimestamp(6, Timestamp.valueOf(this.lastEdited));
				query.setTimestamp(7, Timestamp.valueOf(this.remindDate));
				query.setBoolean(8, this.status);
				query.setString(9, this.name);
				query.setString(10, this.description);
				query.setInt(11, this.subject.getID());
				query.setTimestamp(12, Timestamp.valueOf(LocalDateTime.now()));
				query.setTimestamp(13, Timestamp.valueOf(this.remindDate));
				query.setBoolean(14, this.status);
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
				this.ID=-123;
				this.name="";
				this.description="";
				this.subject=new Subject();
				this.creationDate=LocalDateTime.MIN;
				this.lastEdited=LocalDateTime.MIN;
				this.remindDate=LocalDateTime.MIN;
				this.status=true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
