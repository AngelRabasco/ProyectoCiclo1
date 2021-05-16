package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils.Connect;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils.Encrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends User {
	private final static String getByID="SELECT * FROM user WHERE ID=?";
	private final static String getByName="SELECT * FROM user WHERE Name LIKE ?";
	private final static String getPassword="SELECT Password FROM user WHERE ID=?";
	private final static String insertUpdate="INSERT INTO user (Name,Password) VALUES (?,?) ON DUPLICATE KEY UPDATE Name=?";
	private final static String delete="DELETE FROM user WHERE ID=?";
	
	public UserDAO(Integer ID, String name, String password) {
		//Genera un UserDAO con todos los valores
		super(ID,name,password);
	}
	public UserDAO(Integer ID, String name) {
		//Genera un UserDAO con todos los valores menos la contraseña
		super(ID,name);
	}
	public UserDAO(String name, String password) {
		//Genera un UserDAO con todos los valores menos la ID
		super(name,password);
	}
	public UserDAO(String name) {
		//Genera un UserDAO con solo el nombre
		super(name);
	}
	public UserDAO(User user) {
		//Genera un UserDAO con un usuario
		this.ID=user.ID;
		this.name=user.name;
		this.password=user.password;
	}
	public UserDAO(Integer ID) {
		//Genera un UserDAO con lo datos obtenidos de la base de datos
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByID);
				query.setInt(1, ID);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					this.ID=rs.getInt("ID");
					this.name=rs.getString("Name");
					this.password=rs.getString("Password");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public UserDAO() {
		//Genera un UserDAO por defecto
		super();
	}
	
	public static List<User> searchByID(Integer ID){
		//Devuelve el usuario cuya ID coincida con la ID itroducida
		List<User> queryResult=new ArrayList<User>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByID);
				query.setInt(1, ID);
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new User(
							rs.getInt("ID"),
							rs.getString("Name"),
							rs.getString("Password")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	public static List<User> searchByName(String name) {
		//Devuelve los usuarios cuyo nombre councida con el nombre introducido
		List<User> queryResult=new ArrayList<User>();
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByName);
				query.setString(1, "%"+name+"%");
				ResultSet rs=query.executeQuery();
				while(rs.next()) {
					queryResult.add(new User(
							rs.getInt("ID"),
							rs.getString("Name"),
							rs.getString("Password")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return queryResult;
	}
	
	public Boolean logIn(String name, String password) {
		//Comprueba que haya un usuario con el mismo nombre y contraseña
		Boolean result=false;
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(getByName);
				query.setString(1, name);
				ResultSet rs=query.executeQuery();
				if(rs.next()) {
					if(checkPassword(password, new User(rs.getInt("ID"),rs.getString("Name"),password))) {
						result=true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public Boolean signUp(String name, String password) {
		//Comprueba que no exista un usuario y lo registra
		Boolean result=false;
		Connection con=Connect.getConnection();
		try {
			if(con!=null) {
				List<User> search=searchByName(name);
				if(search.isEmpty()==false) {
					if(search.get(0).name.equals(name)) {
						result=true;
					}else{
						new UserDAO(name,Encrypt.enrypt(password)).save();
					}
				}else{
					new UserDAO(name,Encrypt.enrypt(password)).save();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public Boolean checkPassword(String password, User user) {
		//Comprueba que la contraseña introducida es igual a la almacenada en la base de datos
		Boolean result=false;
		Connection con=Connect.getConnection();
		if(con!=null) {
			PreparedStatement query;
			try {
				query=con.prepareStatement(getPassword);
				query.setInt(1, user.ID);
				ResultSet rs=query.executeQuery();
				if(rs.next()){
					if(new BCryptPasswordEncoder().matches(password, rs.getString("Password"))) {
						result=true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int save() {
		//Guarda el usuario
		int result=0;
		Connection con=Connect.getConnection();
		if(con!=null) {
			try {
				PreparedStatement query=con.prepareStatement(insertUpdate);
				query.setString(1, this.name);
				query.setString(2, this.password);
				query.setString(3, this.name);
				result=query.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int remove() {
		//Elimina el usuario
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
