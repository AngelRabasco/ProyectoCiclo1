package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.SubjectDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntryDAO extends Entry {
	private final static String getByID="SELECT * FROM entry WHERE ID=?";
	private final static String getByName="SELECT * FROM entry WHERE Name=?";
	private final static String getBySubject="SELECT * FROM entry WHERE Subject=?";
	private final static String insertUpdate="INSERT INTO entry (ID,Name,Description,Subject,CreationDate,ReminderTime,Status) VALUES (?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE Name=?,Description=?,Subject=?,ReminderTime=?,Status=?";
	private final static String delete="DELETE FROM entry WHERE ID=?";
	
	public EntryDAO(Integer ID, String name, String description, Subject subject,LocalDateTime creation, LocalDateTime edited) {
		super(ID, name, description, subject, creation, edited);
	}
	public EntryDAO(String name, String description, Subject subject,LocalDateTime creation, LocalDateTime edited) {
		super(name, description, subject, creation, edited);
	}
	public EntryDAO(Entry entry) {
		this.ID=entry.ID;
		this.name=entry.name;
		this.description=entry.description;
		this.subject=entry.subject;
		this.creationDate=entry.creationDate;
		this.lastEdited=entry.lastEdited;
	}
	public EntryDAO(Integer ID) {
		Entry entry=searchByID(ID).get(0);
		this.ID=entry.ID;
		this.name=entry.name;
		this.description=entry.description;
		this.subject=entry.subject;
		this.creationDate=entry.creationDate;
		this.lastEdited=entry.lastEdited;
	}
	public EntryDAO() {
		super();
	}
	
	public static List<Entry> searchByID(Integer ID){
		List<Entry> queryResult=new ArrayList<Entry>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			PreparedStatement query;
			try {
				query = con.prepareStatement(getByID);
				query.setInt(1, ID);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new Entry(
							rs.getInt("ID"),
							rs.getString("Name"),
							rs.getString("Description"),
							SubjectDAO.searchByID(rs.getInt("Subject")).get(0),
							rs.getTimestamp("CreationDate").toLocalDateTime(),
							rs.getTimestamp("LastEdited").toLocalDateTime()));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
}
