package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.SubjectDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntryDAO extends Entry {
	private final static String getByID="SELECT * FROM entry WHERE ID=? AND ReminderTime IS NULL";
	private final static String getByName="SELECT * FROM entry WHERE Name=? AND ReminderTime IS NULL";
	private final static String getBySubject="SELECT * FROM entry WHERE Subject=? AND ReminderTime IS NULL";
	private final static String insertUpdate="INSERT INTO entry (ID,Name,Description,Subject,CreationDate,LastEdited) VALUES (?,?,?,?,?,?) ON DUPLICATE KEY UPDATE Name=?,Description=?,Subject=?,LastEdited=?";
	private final static String delete="DELETE FROM entry WHERE ID=?";
	
	public EntryDAO(Integer ID, String name, String description, Subject subject, LocalDateTime creation, LocalDateTime edited) {
		//Genera una EntryDAO con todos los valores
		super(ID, name, description, subject, creation, edited);
	}
	public EntryDAO(Integer ID, String name, String description, Subject subject) {
		//Genera una EntryDAO con todos los valores menos la fechas
		super(ID,name,description,subject);
	}
	public EntryDAO(Integer ID, String name, String description, LocalDateTime creation, LocalDateTime edited) {
		//Genera una EntryDAO con todos los valores menos la asignatura
			super(ID, name, description, creation, edited);
	}
	public EntryDAO(String name, String description, Subject subject) {
		//Genera una EntryDAO con todos los valores menos la ID y las fechas
		super(name,description,subject);
	}
	public EntryDAO(String name, String description, Subject subject,LocalDateTime creation, LocalDateTime edited) {
		//Genera una EntryDAO con todos los valores menos la ID
		super(name, description, subject, creation, edited);
	}
	public EntryDAO(Entry entry) {
		//Genera una EntryDAO con una entrada
		this.ID=entry.ID;
		this.name=entry.name;
		this.description=entry.description;
		this.subject=entry.subject;
		this.creationDate=entry.creationDate;
		this.lastEdited=entry.lastEdited;
	}
	public EntryDAO(Integer ID) {
		//Genera una EntryDAO con los datos recogidos de la base de datos
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query = con.prepareStatement(getByID);
				query.setInt(1, ID);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					this.ID=rs.getInt("ID");
					this.name=rs.getString("Name");
					this.description=rs.getString("Description");
					this.subject=new SubjectDAO(rs.getInt("ID"));
					this.creationDate=rs.getTimestamp("CreationDate").toLocalDateTime();
					this.lastEdited=rs.getTimestamp("LastEdited").toLocalDateTime();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public EntryDAO() {
		//Genera una EntryDAO por defecto
		super();
	}
	
	public static List<Entry> searchByID(Integer ID){
		//Devuelve la entrada que coincida con la ID introducida
		List<Entry> queryResult=new ArrayList<Entry>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByID);
				query.setInt(1, ID);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new Entry(
							rs.getInt("ID"),
							rs.getString("Name"),
							rs.getString("Description"),
							new SubjectDAO(rs.getInt("Subject")),
							rs.getTimestamp("CreationDate").toLocalDateTime(),
							rs.getTimestamp("LastEdited").toLocalDateTime()));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	public static List<Entry> searchByName(String name) {
		//Devuelve las entradas que coincidan con el nombre introducido
		List<Entry> queryResult=new ArrayList<Entry>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByName);
				query.setString(1, name);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new Entry(
							rs.getInt("ID"),
							rs.getString("Name"),
							rs.getString("Description"),
							new SubjectDAO(rs.getInt("Subject")),
							rs.getTimestamp("CreationDate").toLocalDateTime(),
							rs.getTimestamp("LastEdited").toLocalDateTime()));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	public static ObservableList<Entry> searchBySubject(Subject subject) {
		//Devuelve las entradas que coincidan con la asignatura introducida
		ObservableList<Entry> queryResult=FXCollections.observableArrayList();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getBySubject);
				query.setInt(1, subject.getID());
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new Entry(
							rs.getInt("ID"),
							rs.getString("Name"),
							rs.getString("Description"),
							new SubjectDAO(rs.getInt("Subject")),
							rs.getTimestamp("CreationDate").toLocalDateTime(),
							rs.getTimestamp("LastEdited").toLocalDateTime()));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	public static Boolean checkExists(String name) {
		//Comprueba si existe alguna entrada con el nombre introducido
		Boolean result=false;
		Connection con=Connect.getConnection();
		try {
			if(con!=null) {
				List<Entry> search=searchByName(name);
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
		//Guarda la entrada en la base de datos
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
				query.setString(3, this.description);
				query.setInt(4, this.subject.getID());
				query.setTimestamp(5, Timestamp.valueOf(this.creationDate));
				query.setTimestamp(6, Timestamp.valueOf(this.lastEdited));
				query.setString(7, this.name);
				query.setString(8, this.description);
				query.setInt(9, this.subject.getID());
				query.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
				result=query.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int remove() {
		//Elimina la entrada en la base de datos
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
